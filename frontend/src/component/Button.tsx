import React from "react";
import MuiButton, { ButtonProps } from "@material-ui/core/Button";
import { CircularProgress } from "@material-ui/core";

interface Props extends ButtonProps {
  loading?: boolean;
}

export function Button({ loading, children, disabled, ...rest }: Props) {
  return (
    <MuiButton {...rest} disabled={disabled || loading}>
      {children}
      {loading && <CircularProgress />}
    </MuiButton>
  );
}
