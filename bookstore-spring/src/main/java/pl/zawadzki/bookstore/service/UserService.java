package pl.zawadzki.bookstore.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zawadzki.bookstore.dto.PasswordDto;
import pl.zawadzki.bookstore.dto.UserDto;
import pl.zawadzki.bookstore.model.User;
import pl.zawadzki.bookstore.repository.UserRepository;

@Service
@Log4j2
public class UserService {

    private CurrentUserService currentUserService;
    private UserRepository repository;
    private  PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(CurrentUserService currentUserService,
                       UserRepository repository,
                       PasswordEncoder encoder) {
        this.currentUserService = currentUserService;
        this.repository = repository;
        this.passwordEncoder = encoder;
    }


    public User getUserInfo(){
        return currentUserService.getCurrentUser();
    }

    public void updateUserInfo(UserDto userDto) {
        User updateUser = currentUserService.getCurrentUser();
        updateUser.setUsername(userDto.getUsername());
        updateUser.setEmail(userDto.getEmail());
        updateUser.setPhone(userDto.getPhone());
        updateUser.setAddress(userDto.getAddress());
        repository.save(updateUser);
    }

    public boolean changePassword(PasswordDto passwordDto) {
        User updateUser = currentUserService.getCurrentUser();
        if(passwordEncoder.matches(passwordDto.getOldPassword(),updateUser.getPassword())){
            String encodedNewPassword = passwordEncoder.encode(passwordDto.getNewPassword());
            updateUser.setPassword(encodedNewPassword);
            repository.save(updateUser);
            return true;
        }
        return false;
    }


}
