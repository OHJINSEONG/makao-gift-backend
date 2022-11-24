package megaptera.makaogift.services;

import megaptera.makaogift.dtos.UserDto;
import megaptera.makaogift.exceptions.SignUpFailed;
import megaptera.makaogift.models.User;
import megaptera.makaogift.models.UserName;
import megaptera.makaogift.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(String name, UserName userName, String password, String reconfirmPassword) {
        if (userRepository.findByUserName(userName).isPresent()) {
            throw new SignUpFailed(userName);
        }

        if (!password.equals(reconfirmPassword)) {
            throw new SignUpFailed(password);
        }
        User user = new User(null, userName, name, 50000L);
        user.changePassword(password, passwordEncoder);

        userRepository.save(user);

        return user;
    }

    public UserDto find(UserName userName) {
        User user = userRepository.findByUserName(userName)
                .orElse(null);

        assert user != null;
        return user.toDto();
    }

    public void order(Long totalPrice, UserName userName) {
        User user = userRepository.getByUserName(userName);
        user.order(totalPrice);
    }
}
