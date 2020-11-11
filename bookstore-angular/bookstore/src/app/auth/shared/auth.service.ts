import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {SignupRequest} from "../signup/signup-request";
import {Observable, throwError} from "rxjs";
import {LoginRequest} from "../login/login-request";
import {LocalStorageService} from "ngx-webstorage";
import {LoginResponse} from "../login/login-response";
import {map} from "rxjs/operators";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  loginRequest: LoginRequest;
  token: string;

  constructor(private httpClient: HttpClient,
              private localStorage: LocalStorageService,
              private cookieService: CookieService) {
    this.cookieService.set('username', this.localStorage.retrieve('username'))
  }

  signup(signupRequest: SignupRequest): Observable<any> {
    return this.httpClient.post("http://localhost:8080/api/auth/register", signupRequest);
  }

  login(loginRequest: LoginRequest): Observable<LoginResponse> {
    const httpHeaders = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      Authorization: this.createBasicAuthToken(loginRequest)
      })
    };

    return this.httpClient.post<LoginResponse>("http://localhost:8080/api/auth/login",loginRequest, httpHeaders)
      .pipe(map(data => {
        this.loginRequest = loginRequest;
        this.localStorage.store('username', data.username);
        this.cookieService.set('username', JSON.stringify(data));
        this.localStorage.store('token', this.token);
        return data;
      }));
  }

  logout() {
    this.localStorage.clear('username');
    this.localStorage.clear('token');
    this.cookieService.delete('username');
  }

  getUsername() {
    return this.localStorage.retrieve('username');
  }

  isLoggedIn(): boolean {
    return this.getUsername() != null;
  }

  private createBasicAuthToken(loginRequest: LoginRequest) {
    this.token = 'Basic ' + window.btoa(loginRequest.username + ":" + loginRequest.password);
    return this.token;
  }
}
