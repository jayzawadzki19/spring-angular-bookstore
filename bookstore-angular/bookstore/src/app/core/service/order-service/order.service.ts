import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {LocalStorageService} from "ngx-webstorage";
import {Observable} from "rxjs";
import {Order} from "../../../shared/model/order/order";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private httpClient: HttpClient, private localStorage: LocalStorageService) { }

  getOrders(page,size): Observable<any> {
    const httpHeaders = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: this.localStorage.retrieve('token')
      })
    };
    let params = new HttpParams();
    params = params.append('page',page);
    params = params.append('size',size);
    return this.httpClient.get('http://localhost:8080/api/order/myOrders',{
      params: params,
      headers: httpHeaders.headers
    });
  }

  getOrder(orderId: number): Observable<Order>{
    const httpHeaders = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: this.localStorage.retrieve('token')
      })
    };
    return this.httpClient.get<Order>(`http://localhost:8080/api/order/byId/${orderId}`,{
      headers: httpHeaders.headers
    });
  }
}
