package pl.zawadzki.bookstore.service;

import pl.zawadzki.bookstore.dto.CartItemDto;
import pl.zawadzki.bookstore.model.Cart;

public interface CartService {
    void addToCart(CartItemDto cartItemDto);
    Cart getCart();
    void removeFromCart(CartItemDto cartItemDto);
    void checkout();
}
