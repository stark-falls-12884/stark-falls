import React from "react";
import { useAppSelector } from "../../store";
import { CircularProgress } from "@material-ui/core";

export function PostTable() {
  const data = useAppSelector((state) => state.post.data);
  return <div>{data ? data.content.map((_) => _.body).join() : <CircularProgress />}</div>;
}
