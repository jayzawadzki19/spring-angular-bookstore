package pl.zawadzki.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.zawadzki.bookstore.config.SwaggerConfig;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner dataLoader(BookRepository bookRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
//        return args -> {
//            Publisher publisher = new Publisher();
//            publisher.setName("P1");
//            publisherRepository.save(publisher);
//            Author author1 = new Author();
//            author1.setName("Marian");
//            authorRepository.save(author1);
//            Book book = new Book();
//            Book book2 = new Book();
//            book.setAuthor(author1);
//            book.setPublisher(publisher);
//            book.setTitle("Test1");
//            book.setBookStock(10);
//            book.setDescription("First added book description");
//            book.setPrice(new BigDecimal(23.12));
//            book2.setAuthor(author1);
//            book2.setPublisher(publisher);
//            book2.setTitle("Second Book");
//            book2.setBookStock(5);
//            book2.setDescription("Description for Second Book");
//            book2.setPrice(new BigDecimal(15.99));
//            bookRepository.save(book);
//            bookRepository.save(book2);
//        };
//    }
}
