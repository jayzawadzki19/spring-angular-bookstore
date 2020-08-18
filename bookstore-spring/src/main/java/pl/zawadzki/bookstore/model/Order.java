package pl.zawadzki.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDateTime createdAt;
    private double finalPrice = 0;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    @ManyToMany
    private List<Book> books;
    private boolean finished;

    public void countFinalPrice() {
        this.books.forEach(book -> finalPrice += book.getPrice());
    }

    public void setCreatedAtTime(){
        this.createdAt = LocalDateTime.now();
    }
}
