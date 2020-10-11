package pl.zawadzki.bookstore.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class CartItemDto {
    @NotEmpty
    private Long bookId;
    @Min(value = 1)
    private int quantity;
}
