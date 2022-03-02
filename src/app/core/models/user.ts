export class User {
  id!: number;
  login!: string;
  email!: string;
  motDePasse!: string;
  constructor(obj?: Partial<User>) {
    if (obj) {
      Object.assign(this, obj);
    }
  }
}
