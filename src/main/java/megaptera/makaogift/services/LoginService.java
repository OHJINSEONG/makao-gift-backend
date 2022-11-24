package megaptera.makaogift.services;

import megaptera.makaogift.exceptions.LogInFailed;
import megaptera.makaogift.models.User;
import megaptera.makaogift.models.UserName;
import megaptera.makaogift.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(UserName userName, String password) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new LogInFailed(userName));

        if (!user.authenticate(password, passwordEncoder)) {
            throw new LogInFailed(password);
        }

        return user;
    }
}
