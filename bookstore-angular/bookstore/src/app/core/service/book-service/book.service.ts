import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private httpClient: HttpClient) {
  }

  getAllBooks(page,size): Observable<any> {
    let params = new HttpParams();
    params = params.append('page',page);
    params = params.append('size',size);
    return this.httpClient.get('http://localhost:8080/api/books/all',{
      params: params
  });
  }
}
