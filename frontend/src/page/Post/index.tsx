import React from "react";
import { useParams } from "react-router-dom";
import { useAppDispatch } from "../../store";
import { getPosts } from "./postSlice";
import { PostTable } from "./Table";

interface PostPagePathParams {
  author?: string;
}

export function Post() {
  const { author } = useParams<PostPagePathParams>();
  const dispatch = useAppDispatch();
  React.useEffect(() => {
    dispatch(getPosts({ author }));
  }, []);

  return <PostTable />;
}
