import { Rent } from "./Rent";

export type Grue = {
  id: number;
  name: string;
  type: string;
  capacity: number;
  location: string;
  available: boolean;
  pricePerMonth: number;
  createdAt: Date;
  updatedAt: Date;
  rents: Rent[];
}