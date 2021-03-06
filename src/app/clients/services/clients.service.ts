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
    return this.update(obj);
  }

  public update(item: Client): Observable<Client> {
    return this.httpClient.put<Client>(`${this.urlApi}/client`, item);
  }

  public add(item: Client): Observable<Client> {
    return this.httpClient.post<Client>(`${this.urlApi}/client`, item);
  }

  public getItemById(id: number) {
    return this.httpClient.get<Client[]>(`${this.urlApi}/client?id=${id}`);
  }

  public delete(item: Client): Observable<any> {
    return this.httpClient.delete(`${this.urlApi}/client?id=${item.id}`, {
      responseType: 'text' as const,
    });
  }
}
