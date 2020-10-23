import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SignupRequest} from "../signup/signup-request";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient) { }

  signup(signupRequest: SignupRequest) : Observable<any>{
    return this.httpClient.post("http://localhost:8080/api/auth/register", signupRequest, {responseType: 'text'});
  }
}
