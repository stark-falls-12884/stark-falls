import wretch from "wretch";

export interface LoginRequest {
  username: string;
  password: string;
}

export class UserService {
  static login(request: LoginRequest): Promise<string> {
    return wretch("/api/user/login").post(request).text();
  }

  static register(request: LoginRequest): Promise<void> {
    return wretch("/api/user/register").post(request).text();
  }

  static logout(): Promise<void> {
    return wretch("/api/user/logout").post();
  }

  static getCurrentUser(): Promise<string | null> {
    return wretch("/api/user/current-user").post().text();
  }
}
