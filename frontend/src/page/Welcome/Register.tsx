import React from "react";
import { Container, TextField } from "@material-ui/core";
import { Button } from "../../component/Button";
import { Link } from "../../component/Link";
import { useForm } from "react-hook-form";
import { LoginRequest } from "../../service/UserService";
import { useAppDispatch } from "../../store";
import { userRegister } from "../../mainSlice";

export function Register() {
  const dispatch = useAppDispatch();
  const { register, handleSubmit } = useForm<LoginRequest>();
  const onSubmit = handleSubmit((data) => dispatch(userRegister(data)));
  return (
    <Container>
      <form onSubmit={onSubmit}>
        <TextField label="Username" {...register("username")} />
        <TextField label="Password" {...register("password")} type="password" />
        <Button variant="contained" type="submit">
          Register
        </Button>
      </form>
      <Link to="/welcome/login">Login</Link>
    </Container>
  );
}
