import React from "react";
import "./App.css";
import { Redirect, Route, Switch } from "react-router-dom";
import { Welcome } from "./page/Welcome";
import { Post } from "./page/Post";
import { AppBar, Button, Toolbar } from "@material-ui/core";
import { useAppDispatch, useAppSelector } from "./store";
import { userLogout } from "./mainSlice";
import { ErrorHandler } from "./ErrorHandler";

function App() {
  const user = useAppSelector((state) => state.main.user);
  const dispatch = useAppDispatch();
  return (
    <div>
      <AppBar position="static">
        <Toolbar>
          <span>InstaPic</span>
          {user && (
            <Button onClick={() => dispatch(userLogout())} style={{ marginLeft: "auto", marginRight: 5 }}>
              Logout
            </Button>
          )}
        </Toolbar>
      </AppBar>

      {user ? (
        <Switch>
          <Route path="/post/:author?">
            <Post />
          </Route>
          <Redirect to="/post" />
        </Switch>
      ) : (
        <Welcome />
      )}
      <ErrorHandler />
    </div>
  );
}

export default App;
