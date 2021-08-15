import { createSlice } from "@reduxjs/toolkit";

interface MainState {
  user: "string" | null;
}

const initialState: MainState = {
  user: null,
};

export const mainSlice = createSlice({
  name: "main",
  initialState,
  reducers: {},
});
