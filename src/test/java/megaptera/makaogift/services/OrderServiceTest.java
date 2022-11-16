package megaptera.makaogift.services;

import java.util.List;
import megaptera.makaogift.dtos.OrdersDto;
import megaptera.makaogift.models.Order;
import megaptera.makaogift.models.UserName;
import megaptera.makaogift.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class OrderServiceTest {
    private OrderRepository orderRepository;
    private OrderService orderService;

    @Test
    void list() {
        UserName userName1 = new UserName("ojseong0828");
        UserName userName2 = new UserName("ojw0828");

        Sort sort = Sort.by("createdAt").descending();

        Pageable pageable = PageRequest.of(0, 8, sort);

        List<Order> orders = List.of(
                new Order(1L, userName1, "오진욱", ""),
                new Order(2L, userName1, "오진욱", ""),
                new Order(3L, userName1, "오진욱", ""),
                new Order(4L, userName1, "오진욱", ""),
                new Order(5L, userName1, "오진욱", ""),
                new Order(6L, userName1, "오진욱", ""),
                new Order(7L, userName1, "오진욱", ""),
                new Order(8L, userName1, "오진욱", ""),
                new Order(9L, userName2, "오진욱", ""),
                new Order(10L, userName2, "오진욱", "")
        );

        Page<Order> page = new PageImpl<>(orders, pageable, 10);

        orderRepository = mock(OrderRepository.class);
        given(orderRepository.findAllByUserName(userName1, pageable))
                .willReturn(page);

        orderService = new OrderService(orderRepository);

        OrdersDto ordersDto = orderService.list(userName1, 0);

        assertThat(ordersDto.getOrderResultDtos()).hasSize(8);
        assertThat(ordersDto.getPageDtos()).hasSize(1);
    }

}