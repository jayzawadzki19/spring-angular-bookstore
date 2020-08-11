package pl.zawadzki.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zawadzki.bookstore.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
