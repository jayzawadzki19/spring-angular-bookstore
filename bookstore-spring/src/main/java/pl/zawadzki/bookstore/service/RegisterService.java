package pl.zawadzki.bookstore.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zawadzki.bookstore.dto.RegisterRequest;
import pl.zawadzki.bookstore.model.User;
import pl.zawadzki.bookstore.repository.UserRepository;

@Service
@AllArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(RegisterRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setActive(true);

        userRepository.save(user);
    }
}
