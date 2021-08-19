import React from "react";
import { useAppSelector } from "./store";
import { useSnackbar } from "notistack";

export function ErrorHandler() {
  const error = useAppSelector((state) => state.main.error);
  const { enqueueSnackbar } = useSnackbar();
  React.useEffect(() => {
    if (error) enqueueSnackbar(error.message, { variant: "error" });
  }, [error]);
  return null;
}
