package pl.zawadzki.bookstore.service;

import pl.zawadzki.bookstore.dto.LoginRequest;
import pl.zawadzki.bookstore.dto.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
}
