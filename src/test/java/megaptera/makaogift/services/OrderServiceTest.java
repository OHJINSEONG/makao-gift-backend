package megaptera.makaogift.services;

import java.util.List;
import megaptera.makaogift.dtos.OrderRegisterDto;
import megaptera.makaogift.dtos.OrdersDto;
import megaptera.makaogift.models.Order;
import megaptera.makaogift.models.UserName;
import megaptera.makaogift.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OrderServiceTest {
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setup() {

    }

    @Test
    void list() {
        UserName userName1 = new UserName("ojseong0828");
        UserName userName2 = new UserName("ojw0828");

        Sort sort = Sort.by("createdAt").descending();

        Pageable pageable = PageRequest.of(0, 8, sort);

        List<Order> orders = List.of(
                new Order(1L, 20000L, "", userName1, "오진욱", "", "", "", ""),
                new Order(2L, 20000L, "", userName1, "오진욱", "", "", "", ""),
                new Order(3L, 20000L, "", userName1, "오진욱", "", "", "", ""),
                new Order(4L, 20000L, "", userName1, "오진욱", "", "", "", ""),
                new Order(5L, 20000L, "", userName1, "오진욱", "", "", "", ""),
                new Order(6L, 20000L, "", userName1, "오진욱", "", "", "", ""),
                new Order(7L, 20000L, "", userName1, "오진욱", "", "", "", ""),
                new Order(8L, 20000L, "", userName1, "오진욱", "", "", "", ""),
                new Order(9L, 20000L, "", userName2, "오진욱", "", "", "", ""),
                new Order(10L, 20000L, "", userName2, "오진욱", "", "", "", "")
        );

        Page<Order> page = new PageImpl<>(orders, pageable, 10);

        orderRepository = mock(OrderRepository.class);
        given(orderRepository.findAllByUserName(userName1, pageable))
                .willReturn(page);

        orderService = new OrderService(orderRepository);

        OrdersDto ordersDto = orderService.list(userName1, 1);

        assertThat(ordersDto.getOrderDtos()).hasSize(10);
        assertThat(ordersDto.getPageDtos()).hasSize(2);
    }

    @Test
    void create() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);

        UserName userName = new UserName("ojseong0828");

        OrderRegisterDto orderRegisterDto = new OrderRegisterDto(1L, 10000L, "", "", "", "", "", "");

        orderService.create(orderRegisterDto,userName);

        verify(orderRepository).save(any());
    }

    @Test
    void findOrder() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);

        UserName userName = new UserName("ojseong0828");

        Long id = 1L;

        given(orderRepository.getReferenceById(id))
                .willReturn(new Order(1L, 20000L, "", userName, "오진욱", "", "", "", ""));

        Order order = orderService.findOrder(id);

        assertThat(order.amount()).isEqualTo(1L);
    }
}
