package pl.zawadzki.bookstore.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zawadzki.bookstore.model.Order;
import pl.zawadzki.bookstore.service.CurrentUserService;
import pl.zawadzki.bookstore.service.OrderService;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
@Slf4j
@CrossOrigin
public class OrderController {

    private final OrderService orderService;
    private final CurrentUserService currentUserService;

    @GetMapping("/all")
    public ResponseEntity<Page<Order>> getUserOrders(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Order> finalPage;
        if (currentUserService.getCurrentUser().getRole().equals("ROLE_USER")) {
            finalPage = orderService.getAllByUsername(currentUserService.getCurrentUser().getUsername(), pageable);
        } else {
            finalPage = orderService.getAll(pageable);
        }

        return new ResponseEntity<>(finalPage, HttpStatus.OK);
    }
}
