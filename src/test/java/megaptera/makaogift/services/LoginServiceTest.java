package megaptera.makaogift.services;

import java.util.Optional;
import megaptera.makaogift.exceptions.LogInFailed;
import megaptera.makaogift.models.User;
import megaptera.makaogift.models.UserName;
import megaptera.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LoginServiceTest {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private LoginService loginService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = new Argon2PasswordEncoder();
        loginService = new LoginService(userRepository, passwordEncoder);
    }

    @Test
    void loginSucess() {
        UserName userName = new UserName("ojseong0828");
        String password = "Wlstjdcjs153!";
        Long amount = 50000L;

        User user = new User(1L, userName, "오진성", amount);
        user.changePassword(password, passwordEncoder);

        given(userRepository.findByUserName(userName))
                .willReturn(Optional.of(user));

        User found = loginService.login(userName, password);

        assertThat(found.amount()).isEqualTo(amount);
    }

    @Test
    void loginErrorWithNotFoundUserName() {
        UserName userName = new UserName("ojs0828");
        String password = "Wlstjdcjs153!";
        Long amount = 50000L;

        User user1 = new User(1L, userName, "오진성", amount);
        user1.changePassword(password, passwordEncoder);

        given(userRepository.findByUserName(userName))
                .willThrow(new LogInFailed(userName));


        assertThrows(LogInFailed.class, () -> {
            loginService.login(userName, password);
        });
    }

    @Test
    void loginErrorWithWrongPassword() {
        UserName userName = new UserName("ojseong0828");
        String wrongPassword = "wrong";
        Long amount = 50000L;

        User user1 = new User(1L, userName, "오진성", amount);
        user1.changePassword(wrongPassword, passwordEncoder);

        given(userRepository.findByUserName(userName))
                .willThrow(new LogInFailed(wrongPassword));


        assertThrows(LogInFailed.class, () -> {
            loginService.login(userName, wrongPassword);
        });
    }
}
