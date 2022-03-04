export class Client {
  // idClient!: number;
  id!: number;
  nom!: string;
  prenom!: string;
  entreprise!: string;
  email!: string;
  telephone!: string;
  mobile!: string;
  actif = true;
  notes!: string;
  constructor(obj?: Partial<Client>) {
    if (obj) {
      Object.assign(this, obj);
    }
  }
}
