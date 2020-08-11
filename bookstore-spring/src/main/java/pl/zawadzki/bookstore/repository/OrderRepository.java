package pl.zawadzki.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zawadzki.bookstore.model.Order;
import pl.zawadzki.bookstore.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Iterable<Order> getAllByUser(User user);
    Iterable<Order> getAllByFinished(boolean finished);
}
