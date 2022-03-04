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

  public changeState(item: Client, state: boolean): Observable<Client> {
    const obj = new Client({ ...item });
    obj.actif = state;
    // obj.idClient = item.id;
    console.log(obj);
    return this.update(obj);
  }

  public update(item: Client): Observable<Client> {
    return this.httpClient.put<Client>(`${this.urlApi}/client`, item);
  }

  public add(item: Client): Observable<Client> {
    return this.httpClient.post<Client>(`${this.urlApi}/client`, item);
  }
}
