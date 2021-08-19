import { createAsyncThunk, createSlice, Dispatch } from "@reduxjs/toolkit";
import { push } from "connected-react-router";
import { GetPostResponse, GetPostsRequest, NewPostRequest, PostService } from "../../service/PostService";
import { RootState } from "../../store";

interface PostSlice {
  request: GetPostsRequest;
  author: string | null;
  data: GetPostResponse | null;
  newPostLoading: boolean;
  openCreatePostDialog: boolean;
}

const initialState: PostSlice = {
  request: {
    page: 0,
    size: 10,
  },
  author: null,
  data: null,
  newPostLoading: false,
  openCreatePostDialog: false,
};

export const onLocationChange = createAsyncThunk<
  unknown,
  { request: Partial<GetPostsRequest>; author: string | null },
  { state: RootState }
>("post/onLocationChange", async ({ request, author }, thunkAPI) => {
  const state = thunkAPI.getState().post;
  return thunkAPI.dispatch(
    getPosts({
      author: author || "",
      request: { ...state.request, ...request },
    })
  );
});

export const getPostsByAuthor = (author: string) => {
  return (dispatch: Dispatch) => {
    dispatch(push(`/post/${author}`, initialState.request));
  };
};

export const searchByAuthor = (author: string) => {
  return (dispatch: Dispatch) => {
    dispatch(push(`/post/${author}`, initialState.request));
  };
};

export const newPost = createAsyncThunk("post/newPost", async (request: NewPostRequest, thunkAPI) => {
  const result = await PostService.newPost(request);
  thunkAPI.dispatch(actions.closeCreatePostDialog());
  return result;
});

const getPosts = createAsyncThunk<
  GetPostResponse,
  { request: GetPostsRequest; author: string | null },
  { state: RootState }
>("post/getPosts", async ({ request, author }) => {
  return await PostService.getPostsByAuthor(author || "", request);
});

export const postSlice = createSlice({
  name: "post",
  initialState,
  reducers: {
    openCreatePostDialog(state) {
      state.openCreatePostDialog = true;
    },
    closeCreatePostDialog(state) {
      state.openCreatePostDialog = false;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(getPosts.pending, (state, action) => {
        state.request = action.meta.arg.request;
        state.author = action.meta.arg.author;
      })
      .addCase(getPosts.fulfilled, (state, action) => {
        state.data = action.payload;
      });

    builder
      .addCase(newPost.pending, (state) => {
        state.newPostLoading = true;
      })
      .addCase(newPost.fulfilled, (state, action) => {
        state.data?.content.push(action.payload);
        state.newPostLoading = false;
      })
      .addCase(newPost.rejected, (state) => {
        state.newPostLoading = false;
      });
  },
});

export const actions = postSlice.actions;
