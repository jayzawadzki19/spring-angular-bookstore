import { Component, OnInit } from '@angular/core';
import {BookService} from "../shared/book.service";
import {BookModel} from "../shared/book-model";

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  books$: Array<BookModel> = [];

  constructor(private bookService: BookService) {
    this.bookService.getAllBooks().subscribe(book => {
      this.books$ = book;
    })
  }

  ngOnInit(): void {
  }

}
