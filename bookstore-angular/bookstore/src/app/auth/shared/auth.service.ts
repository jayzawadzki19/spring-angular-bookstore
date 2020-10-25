import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SignupRequest} from "../signup/signup-request";
import {Observable} from "rxjs";
import {LoginRequest} from "../login/login-request";
import {LocalStorageService} from "ngx-webstorage";
import {LoginResponse} from "../login/login-response";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient, private localStorage: LocalStorageService) { }

  signup(signupRequest: SignupRequest) : Observable<any>{
    return this.httpClient.post("http://localhost:8080/api/auth/register", signupRequest, {responseType: 'text'});
  }

  login(loginRequest: LoginRequest) : Observable<boolean> {
    return this.httpClient.post<LoginResponse>("http://localhost:8080/api/auth/login", loginRequest)
      .pipe(map(data =>{
        this.localStorage.store('username',data.username);
        return true;
      }));
  }
}
