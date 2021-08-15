import { configureStore } from "@reduxjs/toolkit";
import { connectRouter, routerMiddleware } from "connected-react-router";
import { createBrowserHistory } from "history";

export const history = createBrowserHistory();

export const store = configureStore({
  reducer: {
    router: connectRouter(history),
  },
  middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(routerMiddleware(history)),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
