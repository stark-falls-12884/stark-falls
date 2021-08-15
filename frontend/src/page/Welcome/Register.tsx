import React from "react";
import { Container } from "@material-ui/core";
import { Button } from "../../component/Button";
import { Link } from "../../component/Link";

export function Register() {
  return (
    <Container>
      <Button variant="contained">Register</Button>
      <Link to="/welcome/login">Login</Link>
    </Container>
  );
}
