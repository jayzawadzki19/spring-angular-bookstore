package pl.zawadzki.bookstore.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zawadzki.bookstore.dto.CartItemDto;
import pl.zawadzki.bookstore.exception.BookNotFoundException;
import pl.zawadzki.bookstore.model.Book;
import pl.zawadzki.bookstore.model.Cart;
import pl.zawadzki.bookstore.model.CartItem;
import pl.zawadzki.bookstore.model.Order;
import pl.zawadzki.bookstore.repository.BookRepository;
import pl.zawadzki.bookstore.repository.CartItemRepository;
import pl.zawadzki.bookstore.repository.CartRepository;
import pl.zawadzki.bookstore.repository.OrderRepository;
import pl.zawadzki.bookstore.service.BookService;
import pl.zawadzki.bookstore.service.CartService;
import pl.zawadzki.bookstore.service.CurrentUserService;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;
    private final CartItemRepository cartItemRepository;
    private final CurrentUserService currentUserService;
    private final OrderRepository orderRepository;

    @Transactional
    public void addToCart(CartItemDto cartItemDto) {
        var book = bookRepository.getBookByBookId(cartItemDto.getBookId());
        if (book == null) {
            throw new BookNotFoundException("Book was not found");
        }
        Cart finalCart = cartRepository.getByUser(currentUserService.getCurrentUser());
        /* Add first item if Set in Cart is empty to avoid iterating on empty Set */
        if (finalCart.getCartItems().size() == 0) {
            CartItem item = createItem(cartItemDto,finalCart,book);
            finalCart.getCartItems().add(item);
            cartItemRepository.save(item);
        } else {
            /*
             * Creates for every cartItem in finalCart Set<CartItem> copy of this Set<> and creates
             * Optional CartItem by filtering along Set with bookId
             */
            finalCart.getCartItems().forEach(cartItem -> {
                Set<CartItem> items = finalCart.getCartItems();
                Optional<CartItem> itemOptional = items.stream().filter(e -> e.getBookId().equals(cartItemDto.getBookId())).findFirst();
                CartItem item;
                /* Increase quantity if item is already in cart */
                if (itemOptional.isPresent()) {
                    item = itemOptional.get();
                    item.setQuantity(cartItem.getQuantity() + cartItemDto.getQuantity());
                }
                /* if not add item to Set */
                else {
                    item = createItem(cartItemDto,finalCart,book);
                    finalCart.getCartItems().add(item);
                }
                cartItemRepository.save(item);
            });
        }
        cartRepository.save(finalCart);
    }

    public Cart getCart() {
        return currentUserService.getCurrentUser().getCart();
    }

    @Transactional
    public void removeFromCart(CartItemDto cartItemDto) {
        Optional<CartItem> optionalCartItem = cartItemRepository.getCartItemByBookId(cartItemDto.getBookId());
        if (optionalCartItem.isEmpty()) {
            throw new BookNotFoundException("Book was not found.");
        }
        optionalCartItem.get().setCart(null);
        getCart().getCartItems().remove(optionalCartItem.get());
        cartItemRepository.deleteById(optionalCartItem.get().getId());

    }

    /** The checkout() method creates Order for current User from User's current Cart status. */
    @Transactional
    public void checkout() {
        Order order = new Order(currentUserService.getCurrentUser());
        orderRepository.save(order);
        getCart().getCartItems().forEach(cartItem -> {
            cartItem.setCart(null);
            cartItem.setOrder(order);
            bookService.decreaseStock(cartItem.getBookId(),cartItem.getQuantity());
            cartItemRepository.save(cartItem);
        });
    }

    private CartItem createItem(CartItemDto cartItemDto, Cart finalCart, Book book){
        return CartItem.builder()
                .bookId(cartItemDto.getBookId())
                .cart(finalCart)
                .bookTitle(book.getTitle())
                .bookAuthor(book.getAuthor().getName())
                .price(book.getPrice())
                .quantity(cartItemDto.getQuantity())
                .build();
    }

}
