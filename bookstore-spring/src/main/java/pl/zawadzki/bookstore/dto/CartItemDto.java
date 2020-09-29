package pl.zawadzki.bookstore.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class CartItem {
    @Min(value = 1)
    private int quantity;
    @NotEmpty
    private Long bookId;
}
