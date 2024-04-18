package megaptera.makaogift.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        passwordEncoder = new Argon2PasswordEncoder();
    }

    @Test
    void changePassworrd() {
        User user = new User(1L, new UserName("ojseong0828"), "오진성", 50000L);

        String password = "password";

        user.changePassword(password, passwordEncoder);

        boolean match = passwordEncoder.matches(password,user.getEncodedPassword());

        assertThat(match).isTrue();
    }

    @Test
    void authenticate() {
        User user = new User(1L, new UserName("ojseong0828"), "오진성", 50000L);

        String password = "password";

        user.changePassword(password, passwordEncoder);

        String rightPassword = "password";
        String wrongPassword = "wrongPassword";

        assertThat(user.authenticate(rightPassword,passwordEncoder)).isTrue();
        assertThat(user.authenticate(wrongPassword,passwordEncoder)).isFalse();
    }
}
