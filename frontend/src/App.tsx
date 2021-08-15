import React from "react";
import "./App.css";
import { Redirect, Route, Switch } from "react-router-dom";
import { Welcome } from "./page/Welcome";
import { Post } from "./page/Post";

function App() {
  return (
    <Switch>
      <Route path="/post">
        <Post />
      </Route>
      <Route path="/welcome/:type?">
        <Welcome />
      </Route>
      <Redirect to="/welcome" />
    </Switch>
  );
}

export default App;
