package pl.zawadzki.bookstore.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.zawadzki.bookstore.dto.LoginRequest;
import pl.zawadzki.bookstore.dto.LoginResponse;

@Service
@AllArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new LoginResponse(loginRequest.getUsername());
    }
}
