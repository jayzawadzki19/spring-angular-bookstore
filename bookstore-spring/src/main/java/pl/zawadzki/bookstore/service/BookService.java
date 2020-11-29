package pl.zawadzki.bookstore.service;

import org.springframework.http.ResponseEntity;
import pl.zawadzki.bookstore.model.Book;

import java.util.List;

public interface BookService {
    ResponseEntity<String> addBook(Book book);
    List<Book> getAll(int page, int size);
    List<Book> getAllByAuthor(String name);
    Book getByTitle(String title);
    void decreaseStock(Long bookId, Integer amount);
}
