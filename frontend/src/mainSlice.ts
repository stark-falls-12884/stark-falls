import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { LoginRequest, UserService } from "./service/UserService";
import { ServerException } from "./service/ServerException";

interface MainState {
  user: string | null;
  error: ServerException | Error | null;
}

const initialState: MainState = {
  user: null,
  error: null,
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

export const getCurrentUser = createAsyncThunk("main/getCurrentUser", async () => {
  try {
    return await UserService.getCurrentUser();
  } catch (e) {
    // No need to log error if user is not logged in
    return;
  }
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
    builder.addCase(getCurrentUser.fulfilled, (state, action) => {
      state.user = action.payload || null;
    });

    builder.addMatcher(
      (action) => action.type.endsWith("rejected"),
      (state, action) => {
        try {
          state.error = JSON.parse(action.error.message);
        } catch (e) {
          state.error = e;
        }
      }
    );
  },
});
