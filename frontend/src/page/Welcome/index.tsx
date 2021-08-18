import React from "react";
import { Login } from "./Login";
import { Register } from "./Register";
import { Container, Link } from "@material-ui/core";

export function Welcome() {
  const [type, setType] = React.useState("login");

  return (
    <Container>
      {type === "login" ? <Login /> : <Register />}
      <div>
        <Link onClick={() => setType(type === "login" ? "register" : "login")}>
          {type === "login" ? "Register" : "Login"}
        </Link>
      </div>
    </Container>
  );
}
