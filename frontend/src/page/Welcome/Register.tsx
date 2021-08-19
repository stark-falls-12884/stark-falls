import React from "react";
import { Container, TextField } from "@material-ui/core";
import { Button } from "../../component/Button";
import { useForm } from "react-hook-form";
import { LoginRequest } from "../../service/UserService";
import { useAppDispatch } from "../../store";
import { userRegister } from "../../mainSlice";

export function Register() {
  const dispatch = useAppDispatch();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<LoginRequest>();
  const onSubmit = handleSubmit((data) => dispatch(userRegister(data)));
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
          Register
        </Button>
      </form>
    </Container>
  );
}
