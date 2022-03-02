import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from 'src/app/core/models/client';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ClientsService {
  private urlApi: string;
  public collection$: Observable<Client[]>;
  // private headers = new HttpHeaders();

  // headers = new HttpHeaders().set('Authorization', 'Bearer POEI');

  constructor(private httpClient: HttpClient) {
    this.urlApi = environment.urlApi;
    this.collection$ = this.httpClient.get<Client[]>(`${this.urlApi}/client`);
  }
}
