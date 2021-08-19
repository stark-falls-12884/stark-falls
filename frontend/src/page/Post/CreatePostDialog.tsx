import React from "react";
import { Dialog, DialogContent, DialogTitle, TextField } from "@material-ui/core";
import { useAppDispatch, useAppSelector } from "../../store";
import { useForm } from "react-hook-form";
import { actions, newPost } from "./postSlice";
import { Button } from "../../component/Button";

interface NewPostRequestForm {
  body?: string;
  file?: FileList;
}

export function CreatePostDialog() {
  const openCreatePostDialog = useAppSelector((state) => state.post.openCreatePostDialog);
  const dispatch = useAppDispatch();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<NewPostRequestForm>();
  const onSubmit = handleSubmit((data) => dispatch(newPost({ body: data.body!, image: data.file!.item(0)! })));

  return (
    <Dialog open={openCreatePostDialog} onClose={() => dispatch(actions.closeCreatePostDialog())}>
      <DialogTitle>Create post</DialogTitle>
      <DialogContent>
        <form onSubmit={onSubmit}>
          <TextField
            required
            error={errors.body != null}
            helperText={errors.body?.message}
            label="Body"
            {...register("body", { required: "Body is required" })}
          />
          <input type="file" {...register("file", { required: true })} />
          <Button type="submit" color="primary">
            Submit
          </Button>
        </form>
      </DialogContent>
    </Dialog>
  );
}
