import { StateOrder } from '../enums/state-order';
import { TypeOrder } from '../enums/type-order';

export interface OrderI {
  id: number;
  label: string;
  tjmht: number;
  dureejours: number;
  tva: number;
  statut: StateOrder;
  typecommande: TypeOrder;
  idclient: number;
  notes: string;
}
