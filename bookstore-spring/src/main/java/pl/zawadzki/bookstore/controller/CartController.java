package pl.zawadzki.bookstore.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zawadzki.bookstore.dto.CartItemDto;
import pl.zawadzki.bookstore.service.CartService;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;


    @GetMapping
    public ResponseEntity showCart(){
        if (cartService.getCart() == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(cartService.getCart());
    }


    @PostMapping("/add/")
    public ResponseEntity addToCart(@RequestBody CartItemDto cartItemDto){
        cartService.addToCart(cartItemDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
