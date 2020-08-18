package pl.zawadzki.bookstore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zawadzki.bookstore.exception.BookNotFoundException;
import pl.zawadzki.bookstore.model.Book;
import pl.zawadzki.bookstore.model.Cart;
import pl.zawadzki.bookstore.repository.BookRepository;
import pl.zawadzki.bookstore.repository.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final CurrentUserService currentUserService;

    public void addToCart(Long id){
        Book book = bookRepository.getBookByBookId(id);
        if(book == null){
            throw new BookNotFoundException("Book does not exist.");
        }
        Optional<Cart> optionalCart = cartRepository.getByUser(currentUserService.getCurrentUser());
        Cart finalCart = optionalCart.orElse(new Cart(currentUserService.getCurrentUser()));

        finalCart.addBook(book);
        cartRepository.save(finalCart);
    }

    public List<Book> getAllCartBooks(){
        Optional<Cart> optionalCart = cartRepository.getByUser(currentUserService.getCurrentUser());
        Cart finalCart = optionalCart.orElse(new Cart(currentUserService.getCurrentUser()));
        return finalCart.getBooks();
    }
}
