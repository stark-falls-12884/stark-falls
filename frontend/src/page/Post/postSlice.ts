import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { GetPostResponse, GetPostsRequest, PostService } from "../../service/PostService";
import { RootState } from "../../store";

interface PostSlice {
  request: GetPostsRequest;
  author: string | null;
  data: GetPostResponse | null;
  loading: boolean;
}

const initialState: PostSlice = {
  request: {
    page: 0,
    size: 10,
  },
  author: null,
  data: null,
  loading: false,
};

export const onLocationChange = createAsyncThunk<
  unknown,
  { request: Partial<GetPostsRequest>; author: string | null },
  { state: RootState }
>("post/onLocationCHange", async ({ request, author }, thunkAPI) => {
  const state = thunkAPI.getState().post;
  return thunkAPI.dispatch(
    getPosts({
      author: author || state.author || "",
      request: { ...state.request, ...request },
    })
  );
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
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(getPosts.fulfilled, (state, action) => {
      state.data = action.payload;
    });
    builder.addCase(getPosts.pending, (state, action) => {
      state.request = action.meta.arg.request;
      state.author = action.meta.arg.author;
    });
  },
});
