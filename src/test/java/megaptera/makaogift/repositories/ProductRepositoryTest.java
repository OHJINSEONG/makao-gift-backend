package megaptera.makaogift.repositories;

import java.time.LocalDateTime;
import megaptera.makaogift.models.Product;
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
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void findAll() {
        Product product = new Product(37000L, "마장동에서 직접 작업하는 1++ 한우(투뿔) 꽃등심 채끝 갈비살 200g (냉장)",
                "주식회사 소소한 형제", "원산지: 국내산 한우(200g)", "이미지");

        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.execute("DELETE FROM product");

        jdbcTemplate.update("INSERT INTO " +
                        "product(" +
                        "id, price, title, manufacturer, imformation, image, " +
                        "created_at, updated_at)" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                1L, product.price(), product.title(), product.manufacturer(),
                product.imformation(), product.image(), now, now
        );

        Sort sort = Sort.by("id").descending();

        Pageable pageable = PageRequest.of(0, 100, sort);

        Page<Product> products = productRepository.findAll(pageable);

        assertThat(products).hasSize(1);
    }
}
