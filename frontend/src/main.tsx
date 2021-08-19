import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import { Provider } from "react-redux";
import { history, store } from "./store";
import { ConnectedRouter } from "connected-react-router";
import { SnackbarProvider } from "notistack";

ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <ConnectedRouter history={history}>
        <SnackbarProvider maxSnack={5}>
          <App />
        </SnackbarProvider>
      </ConnectedRouter>
    </Provider>
  </React.StrictMode>,
  document.getElementById("root")
);
