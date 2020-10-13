package pl.zawadzki.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.zawadzki.bookstore.model.Author;
import pl.zawadzki.bookstore.model.Book;
import pl.zawadzki.bookstore.model.Publisher;
import pl.zawadzki.bookstore.repository.AuthorRepository;
import pl.zawadzki.bookstore.repository.BookRepository;
import pl.zawadzki.bookstore.repository.PublisherRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(BookRepository bookRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Publisher publisher = new Publisher();
                publisher.setName("P1");
                publisherRepository.save(publisher);
                Author author1 = new Author();
                author1.setName("Marian");
                authorRepository.save(author1);
                Book book = new Book();
                book.setAuthor(author1);
                book.setPublisher(publisher);
                book.setTitle("Test1");
                book.setBookStock(10);
                book.setDescription("Description");
                book.setPrice(new BigDecimal(23.12));
                bookRepository.save(book);
            }
        };
    }
}
