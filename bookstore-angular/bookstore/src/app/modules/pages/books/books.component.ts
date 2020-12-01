import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {BookService} from "../../../core/service/book-service/book.service";
import {BookModel} from "../../../shared/model/book/book-model";
import {CartService} from "../../../core/service/cart-service/cart.service";
import {CartDTO} from "../../../core/service/cart-service/cartDTO";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription, throwError} from "rxjs";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit, OnDestroy {

  books$: Array<BookModel> = [];
  page: number = 1;
  size: number = 6;
  cartDTO: CartDTO;
  cartForm: FormGroup;
  private querySub: Subscription;



  constructor(private bookService: BookService,
              private cartService: CartService,
              private route: ActivatedRoute) {
    this.cartDTO = {
      bookId: 0,
      quantity: 0
    };
  }

  ngOnInit(): void {
    this.cartForm = new FormGroup({
      quantity: new FormControl(0, [Validators.required]),
    });
    this.querySub = this.route.queryParams.subscribe(() => {
      this.update()
    })
  }

  ngOnDestroy(): void {
    this.querySub.unsubscribe();
  }


  update() {
    if(this.route.snapshot.queryParamMap.get('page')) {
      const currentPage = +this.route.snapshot.queryParamMap.get('page');
      const size = +this.route.snapshot.queryParamMap.get('size');
      this.getBooks(currentPage,size);
    } else {
      this.getBooks();
    }
  }

  getBooks(page: number = 1, size: number = 6) {
    this.bookService.getAllBooks(page, size).subscribe(book => {
      this.books$ = book;
      this.page = page;
    });
  }

  addToCart(bookId: number) {
    console.log("bookId " + this.cartDTO.bookId);
    console.log("quantity " + this.cartDTO.quantity);
    this.cartDTO.bookId = bookId;
    this.cartDTO.quantity = this.cartForm.get('quantity').value;

    if (this.cartDTO.quantity != 0) {
      this.cartService.addToCart(this.cartDTO).subscribe(data => {
        console.log(data)
      }, error => {
        throwError(error);
      });
    }
  }


}
