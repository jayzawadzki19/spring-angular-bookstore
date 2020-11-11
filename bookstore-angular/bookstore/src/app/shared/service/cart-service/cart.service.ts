import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CartDTO} from "./cartDTO";
import {Observable} from "rxjs";
import {LocalStorageService} from "ngx-webstorage";

@Injectable({
  providedIn: 'root'
})
export class CartService {


  constructor(private httpClient: HttpClient, private localStorage: LocalStorageService) {

  }

  addToCart(cartDTO: CartDTO): Observable<CartDTO> {
    const httpHeaders = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: this.localStorage.retrieve('token')
      })
    };
    console.log(cartDTO);
    return this.httpClient.post<CartDTO>("http://localhost:8080/api/cart/add", cartDTO, httpHeaders);
  }
}
