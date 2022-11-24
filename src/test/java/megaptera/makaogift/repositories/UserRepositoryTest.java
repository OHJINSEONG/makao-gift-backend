package megaptera.makaogift.repositories;

import java.time.LocalDateTime;
import megaptera.makaogift.models.User;
import megaptera.makaogift.models.UserName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void findByUserName() {
        passwordEncoder = new Argon2PasswordEncoder();

        UserName userName = new UserName("ojseong0828");

        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.execute("DELETE FROM person");

        jdbcTemplate.update("INSERT INTO " +
                        "person(" +
                        "id, amount, encoded_password, name, user_name, " +
                        "created_at, updated_at" +
                        ")" +
                        "VALUES(1, ?, ?, ?, ?, ?, ?)",
                50000L, passwordEncoder.encode("Wlstjdcjs153!"), "오진성", userName.value(),
                now, now
        );
    }

    @Test
    void getByUserName() {
        passwordEncoder = new Argon2PasswordEncoder();

        UserName userName = new UserName("ojseong0828");

        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.execute("DELETE FROM person");

        jdbcTemplate.update("INSERT INTO " +
                        "person(" +
                        "id, amount, encoded_password, name, user_name, " +
                        "created_at, updated_at" +
                        ")" +
                        "VALUES(1, ?, ?, ?, ?, ?, ?)",
                50000L, passwordEncoder.encode("Wlstjdcjs153!"), "오진성", userName.value(),
                now, now
        );

        User user = userRepository.getByUserName(userName);

        assertThat(user.name()).isEqualTo("오진성");
    }
}
