package pl.zawadzki.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 5, message = "Password must contain at least 5 characters")
    private String password;

    @Email
    @NotEmpty(message = "Email is required")
    private String email;

    @Pattern(regexp = "\\d{9}")
    private String phone;

    private boolean isActive;
    private final LocalDateTime createdAt = LocalDateTime.now();

    @NotEmpty
    private String role = "ROLE_USER";

}
