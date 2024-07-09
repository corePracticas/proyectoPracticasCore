import { Rent } from "./Rent";

export type Client = {
  id: number;
  name: string;
  email: string;
  password: string;
  createdAt: Date;
  updatedAt: Date;
  rents: Rent[];
}

export type ClientLogin = {
  email: string;
  password: string;
}