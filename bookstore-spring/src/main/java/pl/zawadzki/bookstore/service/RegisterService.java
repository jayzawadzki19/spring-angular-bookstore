package pl.zawadzki.bookstore.service;

import org.springframework.http.ResponseEntity;
import pl.zawadzki.bookstore.dto.RegisterRequest;

public interface RegisterService {
    ResponseEntity<String> signUp(RegisterRequest registerRequest);
}
