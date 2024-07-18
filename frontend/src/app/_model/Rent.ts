import { Client } from "./Client";
import { Grue } from "./Grue";

export enum RentStatus{
  PENDING = 'PENDING',
  CONFIRMED = 'CONFIRMED',
  CANCELED = 'CANCELED',
}
export type RentForm = {
  startDate: Date | null,
  endDate: Date | null,
  months: number,
}
export type Rent = {
  id?: number;
  startDate: Date | null;
  endDate: Date | null;
  totalPrice: number;
  status: RentStatus;
  createdAt: Date;
  updatedAt: Date;
  client: Client | null | undefined; 
  grue: Grue | null | undefined;
}