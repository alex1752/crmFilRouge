import { StateOrder } from '../enums/state-order';
import { TypeOrder } from '../enums/type-order';

export class Order {
  id!: number;
  label!: string;
  tjmht = 1200;
  dureejours = 1;
  tva = 20;
  statut = StateOrder.OPTION;
  typecommande = TypeOrder.FORMATION;
  idclient!: number;
  notes!: string;
  constructor(obj?: Partial<Order>) {
    if (obj) {
      Object.assign(this, obj);
    }
  }
}
