package pl.zawadzki.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zawadzki.bookstore.model.Order;
import pl.zawadzki.bookstore.model.User;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Iterable<Order> getAllByUser(User user);
    Iterable<Order> getAllByUser_Username(String username);
    Iterable<Order> getAllByFinished(boolean finished);
    Optional<Order> getOrderByOrderId(Long id);
}
