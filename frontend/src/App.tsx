import React from "react";
import "./App.css";
import { Redirect, Route, Switch } from "react-router-dom";
import { Welcome } from "./page/Welcome";
import { Post } from "./page/Post";
import { AppBar } from "@material-ui/core";

function App() {
  return (
    <div>
      <AppBar position="static">InstaPic</AppBar>
      <Switch>
        <Route path="/post">
          <Post />
        </Route>
        <Route path="/welcome/:type?">
          <Welcome />
        </Route>
        <Redirect to="/welcome" />
      </Switch>
    </div>
  );
}

export default App;
