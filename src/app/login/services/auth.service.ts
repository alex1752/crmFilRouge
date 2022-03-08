import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private urlApi: string;
  private token!: string;
  private user!: any;

  constructor(private httpClient: HttpClient) {
    this.urlApi = environment.urlApi;
  }

  public login(login: string, motDePasse: string): Observable<any> {
    const body = new HttpParams()
      .set('login', login)
      .set('motDePasse', motDePasse);

    return this.httpClient.post(`${this.urlApi}/login`, body, {
      headers: new HttpHeaders().set(
        'Content-Type',
        'application/x-www-form-urlencoded'
      ),
    });
  }

  public logout() {
    this.token = '';
    this.user = undefined;
  }

  public setAuthToken(token: string, user: string) {
    this.token = token;
    this.user = user;
  }

  public getAuthToken(): string {
    return this.token;
  }

  public getAuthUser(): any {
    return this.user;
  }
}
