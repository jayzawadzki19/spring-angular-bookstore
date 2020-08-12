package pl.zawadzki.bookstore.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<String> signUp(RegisterRequest registerRequest){
        if(userExist(registerRequest)){
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setActive(true);
        userRepository.save(user);
        return new ResponseEntity<>("Registration Successful", HttpStatus.OK);
    }

    private boolean userExist(RegisterRequest registerRequest){
        return
                userRepository.existsByUsername(registerRequest.getUsername())
                || userRepository.existsByEmail(registerRequest.getEmail());
    }
}
