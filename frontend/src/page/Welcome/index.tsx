import React from "react";
import { useParams } from "react-router-dom";
import { Login } from "./Login";
import { Register } from "./Register";
import { Container } from "@material-ui/core";

interface WelcomePagePathParams {
  type?: string;
}

export function Welcome() {
  const { type = "login" } = useParams<WelcomePagePathParams>();
  return <Container>{type === "login" ? <Login /> : <Register />}</Container>;
}
