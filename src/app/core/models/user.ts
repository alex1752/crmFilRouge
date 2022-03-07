export class User {
  id!: number;
  login = 'login';
  email = 'user@example.com';
  motDePasse!: string;
  constructor(obj?: Partial<User>) {
    if (obj) {
      Object.assign(this, obj);
    }
  }
}
