package megaptera.makaogift.repositories;

import java.time.LocalDateTime;
import megaptera.makaogift.models.Order;
import megaptera.makaogift.models.UserName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void findAllByUserName() {
        UserName sender = new UserName("ojseong0828");

        Order order = new Order(1L, 37000L, "주식회사 소소한 형제", sender,
                "마장동에서 직접 작업하는 1++ 한우(투뿔) 꽃등심 채끝 갈비살 200g (냉장)", "세종시", "오진성", "잘먹어", "이미지");

        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.execute("DELETE FROM gift");

        jdbcTemplate.update("INSERT INTO " +
                        "gift(" +
                        "id, amount, totalPrice, manufacturer, sender, title, address, receiver, message, image, " +
                        "created_at, updated_at)" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                1L, order.amount(), order.totalPrice(), order.manufacturer(), "ojseong0828",
                order.title(), "세종시", "오진성", "잘먹어라", order.image(),
                now, now
        );

        Sort sort = Sort.by("createdAt").descending();

        Pageable pageable = PageRequest.of(0, 100, sort);

        Page<Order> orders = orderRepository.findAllByUserName(sender, pageable);

        assertThat(orders).hasSize(1);
    }
}
