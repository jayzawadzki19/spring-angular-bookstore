import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BookService} from "../shared/service/book-service/book.service";
import {BookModel} from "../shared/model/book/book-model";

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  books$: Array<BookModel> = [];
  p: number = 1;

  constructor(private bookService: BookService) {
    this.bookService.getAllBooks().subscribe(book => {
      this.books$ = book;
    })
  }

  ngOnInit(): void {
  }

}
