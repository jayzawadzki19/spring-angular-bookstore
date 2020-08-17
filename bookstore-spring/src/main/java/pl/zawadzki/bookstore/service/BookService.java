package pl.zawadzki.bookstore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zawadzki.bookstore.repository.BookRepository;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

}
