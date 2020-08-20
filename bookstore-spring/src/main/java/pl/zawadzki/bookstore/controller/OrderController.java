package pl.zawadzki.bookstore.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zawadzki.bookstore.dto.OrderDto;
import pl.zawadzki.bookstore.model.Order;
import pl.zawadzki.bookstore.service.OrderService;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(){
        OrderDto orderDto = orderService.createOrder(new OrderDto());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderDto);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Order>> getAllOrders(){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllUserOrders());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity CartDoesNotExistExc(RuntimeException e){
        return ResponseEntity.badRequest().header(e.getMessage()).build();
    }
}
