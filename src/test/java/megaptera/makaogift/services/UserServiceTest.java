package megaptera.makaogift.services;

import java.util.Optional;
import megaptera.makaogift.exceptions.SignUpFailed;
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

class UserServiceTest {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = new Argon2PasswordEncoder();
        userService = new UserService(userRepository, passwordEncoder);

        String name = "오진성";
        UserName userName = new UserName("ojseong0828");
        String password = "Wlstjdcjs153!";
        String reconfirmPassword = "Wlstjdcjs153!";
        Long amount = 50000L;

        User user = new User(1L, userName, "오진성", amount);
        user.changePassword(password, passwordEncoder);

        given(userRepository.findByUserName(userName))
                .willReturn(Optional.of(user));
    }

    @Test
    void createSucess() {
        String name = "오진성";
        UserName userName = new UserName("ojs0828");
        String password = "Wlstjdcjs153!";
        String reconfirmPassword = "Wlstjdcjs153!";
        Long amount = 50000L;

        User found = userService.create(name, userName, password, reconfirmPassword);

        assertThat(found.amount()).isEqualTo(amount);
    }

    @Test
    void createErrorWithExistUserName() {
        String name = "오진성";
        UserName userName = new UserName("ojseong0828");
        String password = "Wlstjdcjs153!";
        String reconfirmPassword = "Wlstjdcjs153!";
        Long amount = 50000L;


        assertThrows(SignUpFailed.class, () -> {
            userService.create(name, userName, password, reconfirmPassword);
        });
    }

    @Test
    void createErrorWithReconfirmFailed() {
        String name = "오진성";
        UserName userName = new UserName("ojs0828");
        String password = "Wlstjdcjs153!";
        String reconfirmPassword = "Wlstjdcjs153!!";
        Long amount = 50000L;


        assertThrows(SignUpFailed.class, () -> {
            userService.create(name, userName, password, reconfirmPassword);
        });
    }
}
