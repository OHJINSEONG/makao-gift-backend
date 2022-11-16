package megaptera.makaogift.backdoor;

import java.time.LocalDateTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("backdoor")
@RestController
@Transactional
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public BackdoorController(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("setup-database")
    public String setupDatabase() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.execute("DELETE FROM person");
        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("DELETE FROM gift");

        jdbcTemplate.update("INSERT INTO " +
                        "person(" +
                        "id, amount, encoded_password, name, user_name, " +
                        "created_at, updated_at" +
                        ")" +
                        "VALUES(1, ?, ?, ?, ?, ?, ?)",
                50000L, passwordEncoder.encode("Wlstjdcjs153!"), "오진성", "ojseong0828",
                now, now
        );

        for (int i = 1; i <= 40; i += 1) {
            Long id = (long) i;

            jdbcTemplate.update("INSERT INTO " +
                            "product(" +
                            "id, name, price, title, manufacturer, imformation, " +
                            "created_at, updated_at)" +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                    id, "오진욱", 10000L, "한우팝니다", "한국", "1등급임",
                    now, now
            );
        }

        for (int i = 1; i <= 3; i += 1) {
            Long id = (long) i;

            jdbcTemplate.update("INSERT INTO " +
                            "gift(" +
                            "id, amount, totalPrice, sender, title, address, receiver, message, " +
                            "created_at, updated_at)" +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    id, 2L, 20000L, "ojseong0828", "한우팝니다", "세종시", "오진성", "잘먹어라",
                    now, now
            );

            jdbcTemplate.update("INSERT INTO " +
                            "gift(" +
                            "id, amount, totalPrice, sender, title, address, receiver, message, " +
                            "created_at, updated_at)" +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    (id + 3L), 2L, 20000L, "ojw0828", "한우팝니다", "세종시", "오진욱", "잘먹어라",
                    now, now
            );
        }


        return "OK";
    }

    @GetMapping("delete-products")
    public String deleteProducts() {
        jdbcTemplate.execute("DELETE FROM product");

        return "OK";
    }

    @GetMapping("delete-orders")
    public String deleteOrders() {
        jdbcTemplate.execute("DELETE FROM gift");

        return "OK";
    }
}
