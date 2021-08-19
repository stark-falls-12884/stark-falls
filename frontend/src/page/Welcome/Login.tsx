import React from "react";
import { Container, TextField } from "@material-ui/core";
import { Button } from "../../component/Button";
import { useAppDispatch } from "../../store";
import { useForm } from "react-hook-form";
import { LoginRequest } from "../../service/UserService";
import { userLogin } from "../../mainSlice";

export function Login() {
  const dispatch = useAppDispatch();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<LoginRequest>();
  const onSubmit = handleSubmit((data) => dispatch(userLogin(data)));

  return (
    <Container>
      <form onSubmit={onSubmit}>
        <TextField
          error={errors.username != null}
          helperText={errors.username?.message}
          label="Username"
          {...register("username", { required: "Username is required" })}
        />
        <TextField
          label="Password"
          error={errors.password != null}
          helperText={errors.password?.message}
          {...register("password", {
            required: "Password must be min length 8",
            minLength: { value: 8, message: "Password must be min length 8" },
          })}
          type="password"
        />
        <Button variant="contained" type="submit">
          Login
        </Button>
      </form>
    </Container>
  );
}
