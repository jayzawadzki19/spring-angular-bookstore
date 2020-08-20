package pl.zawadzki.bookstore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zawadzki.bookstore.exception.AuthorNotFoundException;
import pl.zawadzki.bookstore.exception.BookNotFoundException;
import pl.zawadzki.bookstore.model.Author;
import pl.zawadzki.bookstore.model.Book;
import pl.zawadzki.bookstore.repository.AuthorRepository;
import pl.zawadzki.bookstore.repository.BookRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> getAllByAuthor(String name){
        Author author = authorRepository.getByName(name)
                .orElseThrow(()->new AuthorNotFoundException("Author " + name + " does not exist."));
        return bookRepository.getAllByAuthor(author);
    }

    @Transactional(readOnly = true)
    public Book getByTitle(String title){
        return bookRepository.getBookByTitle(title)
                .orElseThrow(()->new BookNotFoundException("Book " + title + " does not exist."));
    }


}
