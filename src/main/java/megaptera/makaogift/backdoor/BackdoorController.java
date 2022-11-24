package megaptera.makaogift.backdoor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import megaptera.makaogift.models.Order;
import megaptera.makaogift.models.Product;
import megaptera.makaogift.models.UserName;
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

        List<Product> products = new ArrayList<>();
        products.add(new Product(37000L, "마장동에서 직접 작업하는 1++ 한우(투뿔) 꽃등심 채끝 갈비살 200g (냉장)", "주식회사 소소한 형제", "원산지: 국내산 한우(200g)", "https://user-images.githubusercontent.com/107606892/202629659-0793be50-14c5-4da1-ab54-824b428fe004.png"));
        products.add(new Product(12000L, "오리지널 닭가슴살 (냉장)", "컬리", "닭가슴살 600g 100g에 2000원", "http://img.choroc.com/newshop/goods/026714/026714_1.jpg"));
        products.add(new Product(37290L, "매일유업 매일두유 99.9", "매일유업", "100g에 273원", "https://img.danawa.com/prod_img/500000/535/306/img/4306535_1.jpg?shrink=330:330&_v=20211123152502"));
        products.add(new Product(12420L, "자연담다 무항생제 구운계란", "자연담다", "개당 중량: 45g(30구)", "https://src.hidoc.co.kr/image/lib/2021/3/19/1616132123214_0.jpg"));
        products.add(new Product(56800L, "푸드그램 제육볶음 6kg(1.5kg 4팩)", "푸드그램", "푸드그램 제육볶음 6KG (1.5KG*4팩) / 중량 6.00kg / 재고 4 박스 ", "https://img.meatbox.co.kr/m/images/itemImage/2021/01/28/16/10/thumb_product_80193_2_20210128161038.jpg"));
        products.add(new Product(280000L, "스페셜 왕갈비세트 (6대)", "송주 가마골", "미국산 2.4kg", "https://user-images.githubusercontent.com/107606892/202668435-c992b19d-c681-4621-818a-2a42e6cd5005.png"));
        products.add(new Product(7800L, "한돈 국내산 돼지목살 500g", "더 푸드 마트", "(두께1cm)", "https://user-images.githubusercontent.com/107606892/202669209-28028e58-5171-4971-a0cc-eb12d9ffd7f9.png"));
        products.add(new Product(4600L, "비비고 깔끔고기만두200g", "CJ", "믿고 먹는 비비고 만두", "https://user-images.githubusercontent.com/107606892/202669708-448e8611-91c1-4790-8843-e2aada98a22e.png"));
        products.add(new Product(14200L, "[국내산 한돈 1+등급] 생등갈비(쪽갈비) 100시간 저온숙성 500g", "엠케이정육", "원산지: 국내산 (500g)", "https://user-images.githubusercontent.com/107606892/202670539-e0d05098-13a2-469d-b8f5-d24ccfcc1b28.png"));
        products.add(new Product(20900L, "임팩트 웨이 프로틴", "마이프로틴", "1마이프로틴 No.1 베스트셀러", "https://user-images.githubusercontent.com/107606892/202672412-d621d467-f447-464b-891e-ad8f766ec07d.png"));

        for (int i = 0; i < 10; i += 1) {
            Long id = (long) i + 1;

            jdbcTemplate.update("INSERT INTO " +
                            "product(" +
                            "id, price, title, manufacturer, imformation, image, " +
                            "created_at, updated_at)" +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                    id, products.get(i).price(), products.get(i).title(), products.get(i).manufacturer(),
                    products.get(i).imformation(), products.get(i).image(), now, now
            );
        }

        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1L, 37000L, "주식회사 소소한 형제", new UserName("ojseong0828"), "마장동에서 직접 작업하는 1++ 한우(투뿔) 꽃등심 채끝 갈비살 200g (냉장)", "세종시", "오진성", "잘먹어", "https://user-images.githubusercontent.com/107606892/202629659-0793be50-14c5-4da1-ab54-824b428fe004.png"));
        orders.add(new Order(2L, 24000L, "컬리", new UserName("ojseong0828"), "오리지널 닭가슴살 (냉장)", "세종시", "오진성", "잘먹어", "http://img.choroc.com/newshop/goods/026714/026714_1.jpg"));
        orders.add(new Order(1L, 37290L, "매일유업", new UserName("ojseong0828"), "매일유업 매일두유 99.9", "세종시", "오진성", "잘먹어라", "https://img.danawa.com/prod_img/500000/535/306/img/4306535_1.jpg?shrink=330:330&_v=20211123152502"));

        for (int i = 0; i < 3; i += 1) {
            Long id = (long) i + 1;

            jdbcTemplate.update("INSERT INTO " +
                            "gift(" +
                            "id, amount, totalPrice, manufacturer, sender, title, address, receiver, message, image, " +
                            "created_at, updated_at)" +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    id, orders.get(i).amount(), orders.get(i).totalPrice(), orders.get(i).manufacturer(), "ojseong0828",
                    orders.get(i).title(), "세종시", "오진성", "잘먹어라", orders.get(i).image(),
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
