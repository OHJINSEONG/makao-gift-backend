package megaptera.makaogift.models;

import megaptera.makaogift.dtos.OrderDto;
import megaptera.makaogift.dtos.OrderResultDto;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    @Test
    void toDto() {
        Order order = new Order(3L,
                30000L,
                "한국",
                new UserName("ojseong0828"),
                "한우",
                "세종",
                "오진성"
                , "맛있게먹어");

        OrderDto orderDto = order.toDto();

        assertThat(orderDto.getManufacturer()).isEqualTo("한국");
    }

    void toResultDto() {
        Order order = new Order(3L,
                30000L,
                "한국",
                new UserName("ojseong0828"),
                "한우",
                "세종",
                "오진성"
                , "맛있게먹어");

        OrderResultDto orderResultDto = order.toResultDto();

        assertThat(orderResultDto.getAmount()).isEqualTo(3L);
    }
}
