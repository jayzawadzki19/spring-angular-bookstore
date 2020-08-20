package pl.zawadzki.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @NonNull
    @OneToOne
    @JsonIgnore
    private User user;

    private double finalPrice = 0.0;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<Book> books = new HashSet<>();

    public void addBook(Book book){
        this.books.add(book);
    }

    public void removeBook(Book book){
        this.books.remove(book);
    }

    public void calculatePrice(){
        this.books.forEach(book -> this.finalPrice += book.getPrice());
    }

}
