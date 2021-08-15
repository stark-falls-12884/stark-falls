import { configureStore } from "@reduxjs/toolkit";
import { connectRouter, routerMiddleware } from "connected-react-router";
import { createBrowserHistory } from "history";
import { TypedUseSelectorHook, useDispatch, useSelector } from "react-redux";
import { mainSlice } from "./mainSlice";

export const history = createBrowserHistory();

export const store = configureStore({
  reducer: {
    main: mainSlice.reducer,
    router: connectRouter<any>(history),
  },
  middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(routerMiddleware(history)),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

export const useAppDispatch = () => useDispatch<AppDispatch>();
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;
