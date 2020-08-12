package pl.zawadzki.bookstore.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zawadzki.bookstore.dto.RegisterRequest;
import pl.zawadzki.bookstore.service.RegisterService;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<String> registerNewUser(@RequestBody RegisterRequest registerRequest){
        return registerService.signUp(registerRequest);
    }

}
