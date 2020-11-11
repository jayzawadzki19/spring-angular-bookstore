import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BookService} from "../shared/service/book-service/book.service";
import {BookModel} from "../shared/model/book/book-model";
import {CartService} from "../shared/service/cart-service/cart.service";
import {CartDTO} from "../shared/service/cart-service/cartDTO";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {throwError} from "rxjs";

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  books$: Array<BookModel> = [];
  p: number = 1;
  cartDTO: CartDTO;
  cartForm: FormGroup;


  constructor(private bookService: BookService,
              private cartService: CartService) {
    this.bookService.getAllBooks().subscribe(book => {
      this.books$ = book;
    });
    this.cartDTO = {
      bookId: 0,
      quantity: 0
    };
  }

  ngOnInit(): void {
    this.cartForm = new FormGroup({
      quantity: new FormControl(0, [Validators.required]),
    });
  }

  addToCart(bookId: number) {
    this.cartDTO.bookId = bookId;
    this.cartDTO.quantity = this.cartForm.get('quantity').value;

    this.cartService.addToCart(this.cartDTO).subscribe(data =>{
      console.log(data)
    }, error => {
      throwError(error);
    });

  }

}
