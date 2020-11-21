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
    return this.httpClient.post<CartDTO>("http://localhost:8080/api/cart/add", cartDTO, httpHeaders);
  }

  getCartItems(): Observable<any> {
    const httpHeaders = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: this.localStorage.retrieve('token')
      })
    };
    return this.httpClient.get('http://localhost:8080/api/cart',httpHeaders);
  }

  removeItem(id: number): Observable<any> {
    const httpHeaders = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: this.localStorage.retrieve('token')
      })
    };
    return this.httpClient.delete(`http://localhost:8080/api/cart/remove/${id}`, httpHeaders);
  }
}
