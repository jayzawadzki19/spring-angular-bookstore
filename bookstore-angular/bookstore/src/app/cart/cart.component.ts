import { Component, OnInit } from '@angular/core';
import {CartService} from "../shared/service/cart-service/cart.service";
import {CartItem} from "../shared/model/cartItem/cart-item";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cart$: Array<CartItem> = [];

  constructor(private cartService: CartService) {
    this.cartService.getCartItems().subscribe(cart => {
      this.cart$ = cart.cartItems;
    });
  }

  ngOnInit(): void {
  }

}
