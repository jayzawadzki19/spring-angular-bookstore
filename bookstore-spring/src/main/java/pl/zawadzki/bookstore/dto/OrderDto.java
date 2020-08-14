package pl.zawadzki.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long orderId;
    private double finalPrice = 0;
    private boolean finished;
    private Integer numberOfBooks;
}
