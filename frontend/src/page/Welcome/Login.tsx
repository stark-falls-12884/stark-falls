import React from "react";
import { Container } from "@material-ui/core";
import { Button } from "../../component/Button";
import { Link } from "../../component/Link";

export function Login() {
  return (
    <Container>
      <Button variant="contained">Login</Button>
      <Link to="/welcome/register">Register</Link>
    </Container>
  );
}
