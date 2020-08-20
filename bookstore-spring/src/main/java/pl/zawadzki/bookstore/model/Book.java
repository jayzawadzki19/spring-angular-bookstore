package pl.zawadzki.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @NotBlank(message = "Title is required")
    private String title;

    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorId", referencedColumnName = "authorId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Author author;

    @OneToOne
    private Publisher publisher;
}
