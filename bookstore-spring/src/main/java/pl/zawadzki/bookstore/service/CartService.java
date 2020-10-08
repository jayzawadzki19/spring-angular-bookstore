package pl.zawadzki.bookstore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zawadzki.bookstore.dto.CartItemDto;
import pl.zawadzki.bookstore.exception.BookNotFoundException;
import pl.zawadzki.bookstore.model.Cart;
import pl.zawadzki.bookstore.model.CartItem;
import pl.zawadzki.bookstore.repository.BookRepository;
import pl.zawadzki.bookstore.repository.CartItemRepository;
import pl.zawadzki.bookstore.repository.CartRepository;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final CartItemRepository cartItemRepository;
    private final CurrentUserService currentUserService;

    @Transactional
    public void addToCart(CartItemDto cartItemDto) {
        var book = bookRepository.getBookByBookId(cartItemDto.getBookId());
        if (book == null) {
            throw new BookNotFoundException("Book was not found");
        }
        Cart finalCart = cartRepository.getByUser(currentUserService.getCurrentUser());
        /**
         * Creates for every cartItem in finalCart Set<CartItem> copy of this Set<> and creates
         * Optional CartItem by filtering along Set with bookId
         */
        finalCart.getCartItems().forEach(cartItem -> {
            Set<CartItem> items = finalCart.getCartItems();
            Optional<CartItem> itemOptional = items.stream().filter(e -> e.getBookId().equals(cartItem.getBookId())).findFirst();
            CartItem item;
            /** Increase quantity if item is already in cart */
            if (itemOptional.isPresent()) {
                item = itemOptional.get();
                item.setQuantity(cartItem.getQuantity() + item.getQuantity());
            }
            /** if not add item to Set */
            else {
                item = cartItem;
                item.setCart(finalCart);
                finalCart.getCartItems().add(item);
            }
            cartItemRepository.save(item);
        });
        cartRepository.save(finalCart);
    }

    public Cart getCart() {
        return currentUserService.getCurrentUser().getCart();
    }

}
