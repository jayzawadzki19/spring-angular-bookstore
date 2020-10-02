package pl.zawadzki.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@Builder
public class CartItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;

    @NotEmpty
    private Long bookId;

    @Min(1)
    private int quantity;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CartItem cartItem = (CartItem) o;
        return quantity == cartItem.quantity &&
                Objects.equals(id, cartItem.id) &&
                Objects.equals(bookId, cartItem.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, bookId, quantity);
    }
}
