import { Client } from "./Client";
import { Grue } from "./Grue";

export enum RentStatus{
  PENDING = 'PENDING',
  CONFIRMED = 'CONFIRMED',
  CANCELED = 'CANCELED',
}
export type Rent = {
  id?: number;
  startDate: Date;
  endDate: Date;
  totalPrice: number;
  status: RentStatus;
  createdAt: Date;
  updatedAt: Date;
  client: Client | null | undefined; 
  grue: Grue | null | undefined;
}