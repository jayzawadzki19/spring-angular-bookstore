package pl.zawadzki.bookstore.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zawadzki.bookstore.exception.AuthorNotFoundException;
import pl.zawadzki.bookstore.exception.BookNotFoundException;
import pl.zawadzki.bookstore.model.Author;
import pl.zawadzki.bookstore.model.Book;
import pl.zawadzki.bookstore.repository.AuthorRepository;
import pl.zawadzki.bookstore.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public ResponseEntity<String> addBook(Book book){
        Optional<Book> optionalBook = bookRepository.findByTitleIgnoreCase(book.getTitle());
        if (optionalBook.isPresent()){
            return new ResponseEntity<>("Book already exists.", HttpStatus.CONFLICT);
        }
        bookRepository.save(book);
        return new ResponseEntity<>("Book has been created", HttpStatus.OK);
    }

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
