import { StateClients } from "../enums/state-clients";

export class Client {
  id!: number;
  nom!: string;
  prenom!: string;
  entreprise!: string;
  email!: string;
  telephone!: string;
  mobile!: string;
  actif!: StateClients;
  notes!: string;
  constructor(obj?: Partial<Client>) {
    if (obj) {
      Object.assign(this, obj);
    }
  }
}
