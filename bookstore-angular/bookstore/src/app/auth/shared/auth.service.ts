import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SignupRequest} from "../signup/signup-request";
import {Observable} from "rxjs";
import {LoginRequest} from "../login/login-request";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient) { }

  signup(signupRequest: SignupRequest) : Observable<any>{
    return this.httpClient.post("http://localhost:8080/api/auth/register", signupRequest, {responseType: 'text'});
  }

  login(loginRequest: LoginRequest) : Observable<any> {
    return this.httpClient.post("http://localhost:8080/api/auth/login", loginRequest, {responseType: 'text'});
  }
}
