package pl.zawadzki.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zawadzki.bookstore.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
