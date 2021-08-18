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
    number: 0,
    size: 10,
  },
  author: null,
  data: null,
  loading: false,
};

export const getPosts = createAsyncThunk<
  GetPostResponse,
  Partial<{ request: GetPostsRequest; author: string | null }>,
  { state: RootState }
>("post/getPosts", async ({ request, author }, thunkAPI) => {
  const state = thunkAPI.getState().post;
  return await PostService.getPostsByAuthor(author || state.author || "", request || state.request);
});

export const postSlice = createSlice({
  name: "post",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(getPosts.fulfilled, (state, action) => {
      state.data = action.payload;
    });
  },
});
