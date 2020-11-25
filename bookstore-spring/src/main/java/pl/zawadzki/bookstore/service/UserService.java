package pl.zawadzki.bookstore.service;

import pl.zawadzki.bookstore.dto.PasswordDto;
import pl.zawadzki.bookstore.dto.UserDto;
import pl.zawadzki.bookstore.model.User;

public interface UserService {
    User getUserInfo();
    void updateUserInfo(UserDto userDto);
    boolean changePassword(PasswordDto passwordDto);

}
