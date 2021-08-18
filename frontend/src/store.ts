import { configureStore } from "@reduxjs/toolkit";
import { connectRouter, routerMiddleware } from "connected-react-router";
import { createBrowserHistory, LocationState } from "history";
import { TypedUseSelectorHook, useDispatch, useSelector } from "react-redux";
import { mainSlice } from "./mainSlice";
import { postSlice } from "./page/Post/postSlice";

export const history = createBrowserHistory();

export const store = configureStore({
  reducer: {
    main: mainSlice.reducer,
    post: postSlice.reducer,
    router: connectRouter<LocationState>(history),
  },
  middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(routerMiddleware(history)),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

export const useAppDispatch = () => useDispatch<AppDispatch>();
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;
