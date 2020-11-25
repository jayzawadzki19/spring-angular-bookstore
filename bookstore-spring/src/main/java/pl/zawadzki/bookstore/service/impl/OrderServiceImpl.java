package pl.zawadzki.bookstore.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.zawadzki.bookstore.model.Order;
import pl.zawadzki.bookstore.repository.OrderRepository;
import pl.zawadzki.bookstore.service.OrderService;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public Page<Order> getAll(Pageable pageable) {
        return orderRepository.findAllByOrderByIsFinishedAscCreatedAtDesc(pageable);
    }

    public Page<Order> getAllByUsername(String username, Pageable pageable) {
        return orderRepository.findAllByBuyerUsernameOrderByIsFinishedAscCreatedAtDesc(username, pageable);
    }

    public Page<Order> getAllByEmail(String email, Pageable pageable) {
        return orderRepository.findAllByBuyerEmailOrderByIsFinishedAscCreatedAtDesc(email, pageable);
    }

    public Page<Order> getAllByStatus(boolean isFinished, Pageable pageable) {
        return orderRepository.findAllByIsFinishedOrderByCreatedAtDesc(isFinished, pageable);
    }


}
