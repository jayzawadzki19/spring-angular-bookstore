import { Component, OnInit } from '@angular/core';
import {CartService} from "../../../core/service/cart-service/cart.service";
import {CartItem} from "../../../shared/model/cartItem/cart-item";
import {AuthService} from "../../../core/authentication/auth.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cart$: Array<CartItem> = [];
  isLogged: boolean;
  constructor(private cartService: CartService,
              private authService: AuthService) {
    this.updateCart();
  }

  ngOnInit(): void {
    this.isLogged = this.authService.isLoggedIn();
  }

  removeFromCart(id: number) {
    this.cartService.removeItem(id).subscribe();
    this.updateCart();
  }

  private updateCart() {
    this.cartService.getCartItems().subscribe(cart => {
      this.cart$ = cart.cartItems;
    });
  }

}
