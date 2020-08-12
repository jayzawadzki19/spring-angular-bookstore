package pl.zawadzki.bookstore.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zawadzki.bookstore.dto.LoginRequest;
import pl.zawadzki.bookstore.dto.LoginResponse;
import pl.zawadzki.bookstore.dto.RegisterRequest;
import pl.zawadzki.bookstore.service.LoginService;
import pl.zawadzki.bookstore.service.RegisterService;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final RegisterService registerService;
    private final LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody RegisterRequest registerRequest){
        return registerService.signUp(registerRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return loginService.login(loginRequest);
    }
}
