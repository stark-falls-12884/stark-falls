import React from "react";
import { useParams } from "react-router-dom";
import { Login } from "./Login";
import { Register } from "./Register";

interface WelcomePagePathParams {
  type?: string;
}

export function Welcome() {
  const { type = "login" } = useParams<WelcomePagePathParams>();
  return type === "login" ? <Login /> : <Register />;
}
