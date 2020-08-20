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
                Author author2 = new Author();
                author2.setName("Grzegorz");
                authorRepository.save(author1);
                authorRepository.save(author2);
                bookRepository.save(new Book(null,"Book1",21.40,
                        author1,publisher));
                bookRepository.save(new Book(null,"Book2",31.40,
                        author1,publisher));
                bookRepository.save(new Book(null,"Book3",41.40,
                        author2,publisher));
            }
        };
    }
}
