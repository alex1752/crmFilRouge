import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StateOrder } from 'src/app/core/enums/state-order';
import { TypeOrder } from 'src/app/core/enums/type-order';
import { Order } from 'src/app/core/models/order';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class OrdersService {
  private urlApi: string;
  public collection$: Observable<Order[]>;
  // private httpOptions = {
  //   headers: new HttpHeaders({
  //     Accept: 'text/html, application/xhtml+xml, */*',
  //     'Content-Type': 'application/x-www-form-urlencoded',
  //   }),
  //   responseType: 'text',
  // };

  // private headers = new HttpHeaders({
  //   Accept: 'text/html, application/xhtml+xml, */*',
  //   'Content-Type': 'application/x-www-form-urlencoded',
  // });

  constructor(private httpClient: HttpClient) {
    this.urlApi = environment.urlApi;
    this.collection$ = this.httpClient.get<Order[]>(`${this.urlApi}/commande`);
  }

  public changeState(item: Order, state: StateOrder): Observable<Order> {
    const obj = new Order({ ...item });
    obj.statut = state;
    return this.update(obj);
  }

  public changeType(item: Order, type: TypeOrder): Observable<Order> {
    const obj = new Order({ ...item });
    obj.typecommande = type;
    return this.update(obj);
  }

  public update(item: Order): Observable<Order> {
    return this.httpClient.put<Order>(`${this.urlApi}/commande`, item);
  }

  public add(item: Order): Observable<Order> {
    return this.httpClient.post<Order>(`${this.urlApi}/commande`, item);
  }

  public delete(item: Order): Observable<any> {
    return this.httpClient.delete(`${this.urlApi}/commande?id=${item.id}`, {
      responseType: 'text' as const,
    });
  }

  public getItemById(id: number) {
    return this.httpClient.get<Order[]>(`${this.urlApi}/commande?id=${id}`);
  }
}
