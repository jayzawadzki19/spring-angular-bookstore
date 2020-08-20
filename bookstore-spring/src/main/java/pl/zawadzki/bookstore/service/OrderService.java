package pl.zawadzki.bookstore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zawadzki.bookstore.dto.OrderDto;
import pl.zawadzki.bookstore.mapper.OrderMapper;
import pl.zawadzki.bookstore.model.Cart;
import pl.zawadzki.bookstore.model.Order;
import pl.zawadzki.bookstore.repository.CartRepository;
import pl.zawadzki.bookstore.repository.OrderRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final CurrentUserService currentUserService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CartRepository cartRepository;

    @Transactional
    public OrderDto createOrder(OrderDto orderDto){
        Order newOrder = orderMapper.mapDtoToOrder(orderDto);
        Optional<Cart> optionalCart = cartRepository.getByUser(currentUserService.getCurrentUser());
        Cart cart = optionalCart.orElseThrow(()->new RuntimeException("Cart does not exist"));
        newOrder.setUser(cart.getUser());
        newOrder.setBooks(cart.getBooks());
        newOrder.setFinalPrice(cart.getFinalPrice());
        newOrder.setCreatedAtTime();
        cartRepository.delete(cart);
        orderRepository.save(newOrder);
        orderDto.setOrderId(newOrder.getOrderId());
        orderDto.setFinalPrice(newOrder.getFinalPrice());
        orderDto.setNumberOfBooks(newOrder.getBooks().size());
        return orderDto;
    }

    @Transactional
    public Iterable<Order> getAllUserOrders(){
        return orderRepository.getAllByUser_Username(currentUserService.getCurrentUser().getUsername());
    }
}
