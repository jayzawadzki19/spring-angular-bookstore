package pl.zawadzki.bookstore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.zawadzki.bookstore.model.Order;

public interface OrderService {
    Page<Order> getAll(Pageable pageable);
    Page<Order> getAllByUsername(String username, Pageable pageable);
    Page<Order> getAllByEmail(String email, Pageable pageable);
    Page<Order> getAllByStatus(boolean isFinished, Pageable pageable);

}
