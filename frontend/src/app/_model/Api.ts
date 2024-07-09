export type ApiResponse = {
  success: boolean;
  message: string;
}

export type ApiJwtResponse = {
  success: boolean;
  message: string;
  token: string;
  username: string;
}