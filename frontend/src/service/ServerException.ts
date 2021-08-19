export interface ServerException {
  timestamp: string;
  status: number;
  error: string;
  message: string;
  path: string;
}
