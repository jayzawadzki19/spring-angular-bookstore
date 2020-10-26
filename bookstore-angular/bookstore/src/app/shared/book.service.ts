import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private httpClient: HttpClient) {
  }

  getAllBooks(): Observable<any> {
    return this.httpClient.get('http://localhost:8080/api/books/all');
  }
}
