package pl.zawadzki.bookstore.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class CartItemDto {
    @NotEmpty
    private Long bookId;
    @NotNull
    private BigDecimal price;
    @Min(value = 1)
    private int quantity;
}
