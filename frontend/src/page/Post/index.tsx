import React from "react";
import { useLocation, useParams } from "react-router-dom";
import { useAppDispatch } from "../../store";
import { onLocationChange } from "./postSlice";
import { PostTable } from "./Table";
import { GetPostsRequest } from "../../service/PostService";
import { CreatePostDialog } from "./CreatePostDialog";

interface PostPagePathParams {
  author?: string;
}

export function Post() {
  const { author = null } = useParams<PostPagePathParams>();
  const location = useLocation<GetPostsRequest | null>();
  const dispatch = useAppDispatch();
  React.useEffect(() => {
    dispatch(onLocationChange({ author, request: location.state || {} }));
  }, [author, location]);

  return (
    <div>
      <PostTable />
      <CreatePostDialog />
    </div>
  );
}
