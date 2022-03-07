import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor() {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    request = request.clone({
      setHeaders: {
        // 'Content-Type': 'application/json; charset=utf-8',
        // Accept: 'application/json;text/plain',
        Authorization: `Bearer POEI`,
        // 'Access-Control-Allow-Origin': '*',
        // 'Access-Control-Allow-Headers': '*, Authorization',
        // 'Access-Control-Allow-Methods':
        //   'GET, OPTIONS, HEAD, PUT, POST, PATCH, DELETE',
        // 'Authorization': `Bearer ${AuthService.getToken()}`,
      },
    });

    // request.headers.append(Authorization, `Bearer POEI`);
    console.log(request.headers.get('Authorization'));
    return next.handle(request);
  }
}
