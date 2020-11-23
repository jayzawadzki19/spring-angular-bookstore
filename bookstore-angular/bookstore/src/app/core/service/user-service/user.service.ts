import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../../../shared/model/user/user";
import {LocalStorageService} from "ngx-webstorage";
import {PasswordDto} from "../../../shared/model/user/passwordDto";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient,
              private localStorage: LocalStorageService) { }


  getUserInfo(): Observable<User> {
    const httpHeaders = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: this.localStorage.retrieve('token')
      })
    };
    return this.httpClient.get<User>('http://localhost:8080/api/user',httpHeaders);
  }

  updateUserInfo(user: User): Observable<User> {
    const httpHeaders = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: this.localStorage.retrieve('token')
      })
    };

    return this.httpClient.post<User>('http://localhost:8080/api/user/update',user,httpHeaders);
  }

  updatePassword(paswordDto: PasswordDto): Observable<PasswordDto> {
    const httpHeaders = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: this.localStorage.retrieve('token')
      })
    };

    return this.httpClient.post<PasswordDto>('http://localhost:8080/api/user/changePassword',paswordDto,httpHeaders);

  }
}
