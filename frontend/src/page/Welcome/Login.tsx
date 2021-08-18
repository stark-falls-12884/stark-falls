import React from "react";
import { Container, TextField } from "@material-ui/core";
import { Button } from "../../component/Button";
import { useAppDispatch } from "../../store";
import { useForm } from "react-hook-form";
import { LoginRequest } from "../../service/UserService";
import { userLogin } from "../../mainSlice";

export function Login() {
  const dispatch = useAppDispatch();
  const { register, handleSubmit } = useForm<LoginRequest>();
  const onSubmit = handleSubmit((data) => dispatch(userLogin(data)));

  return (
    <Container>
      <form onSubmit={onSubmit}>
        <TextField label="Username" {...register("username")} />
        <TextField label="Password" {...register("password")} type="password" />
        <Button variant="contained" type="submit">
          Login
        </Button>
      </form>
    </Container>
  );
}
