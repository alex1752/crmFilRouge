import { StateClients } from '../enums/state-clients';

export interface ClientI {
  id: number;
  nom: string;
  prenom: string;
  entreprise: string;
  email: string;
  telephone: string;
  mobile: string;
  actif: StateClients;
  notes: string;
}
