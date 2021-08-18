import wretch from "wretch";

export interface GetPostsRequest {
  number: number;
  size: number;
}

export interface NewPostRequest {
  body: string;
  image: File; // TODO: Proper type
}

export interface Post {
  body: string;
  createdTime: string;
  id: number;
  authorName: string;
  imageUrl: string;
}

export interface GetPostResponse {
  totalPages: number;
  totalElements: number;
  number: number;
  size: number;
  numberOfElements: number;
  content: Post[];
}

export class PostService {
  static getPosts(request: GetPostsRequest): Promise<GetPostResponse> {
    return wretch("/api/post").query(request).get().json();
  }

  static getPostsByAuthor(author: string, request: GetPostsRequest): Promise<GetPostResponse> {
    return wretch(`/api/post/${author}`).query(request).get().json();
  }

  static newPost(request: NewPostRequest) {
    const formData = new FormData();
    formData.set("body", request.body);
    formData.set("image", request.image);
    return wretch(`/api/post`).formData(formData).post();
  }
}
