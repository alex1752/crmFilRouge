import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    const token = this.authService.getAuthToken();

    if (token) {
      request = request.clone({
        setHeaders: {
          // 'Content-Type': 'application/json; charset=utf-8',
          // Accept: 'application/json;text/plain',
          // Authorization: `Bearer POEI`,
          // 'Access-Control-Allow-Origin': '*',
          // 'Access-Control-Allow-Headers': '*, Authorization',
          // 'Access-Control-Allow-Methods':
          //   'GET, OPTIONS, HEAD, PUT, POST, PATCH, DELETE',
          Authorization: `Bearer ${token}`,
        },
      });
    }

    // request.headers.append(Authorization, `Bearer POEI`);
    return next.handle(request);
  }
}
