package pl.zawadzki.bookstore.service;

import org.springframework.data.domain.Pageable;
import pl.zawadzki.bookstore.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll(int page, int size);
    List<Order> getAllByUsername(int page, int size);
    List<Order> getAllByEmail(String email, Pageable pageable);
    List<Order> getAllByStatus(boolean isFinished, Pageable pageable);
    Order getById(Long id);

}
