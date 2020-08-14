package pl.zawadzki.bookstore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zawadzki.bookstore.mapper.OrderMapper;
import pl.zawadzki.bookstore.repository.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

}
