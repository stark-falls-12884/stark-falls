import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { LoginRequest, UserService } from "./service/UserService";

interface MainState {
  user: string | null;
}

const initialState: MainState = {
  user: null,
};

export const userLogin = createAsyncThunk("main/userLogin", async (request: LoginRequest) => {
  const result = await UserService.login(request);
  return result;
});

export const userRegister = createAsyncThunk("main/register", async (request: LoginRequest) => {
  const result = await UserService.register(request);
  return result;
});

export const userLogout = createAsyncThunk("main/userLogout", async () => {
  return await UserService.logout();
});

export const mainSlice = createSlice({
  name: "main",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(userLogin.fulfilled, (state, action) => {
      state.user = action.payload;
    });
    builder.addCase(userLogout.fulfilled, (state) => {
      state.user = null;
    });
  },
});
