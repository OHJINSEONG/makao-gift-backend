package megaptera.makaogift.services;

import java.util.ArrayList;
import java.util.List;
import megaptera.makaogift.dtos.OrderDto;
import megaptera.makaogift.dtos.OrderRegisterDto;
import megaptera.makaogift.dtos.OrdersDto;
import megaptera.makaogift.dtos.PageDto;
import megaptera.makaogift.models.Order;
import megaptera.makaogift.models.UserName;
import megaptera.makaogift.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrdersDto list(UserName sender, int page) {
        Sort sort = Sort.by("createdAt").descending();

        Pageable pageable = PageRequest.of(page - 1, 8, sort);

        Page<Order> orders = orderRepository.findAllByUserName(sender, pageable);

        List<OrderDto> orderDtos = orders.stream().map(Order::toDto).toList();

        List<PageDto> pageDtos = new ArrayList<>();
        for (int i = 1; i <= orders.getTotalPages(); i += 1) {
            PageDto pageDto = new PageDto(i);
            pageDtos.add(pageDto);
        }

        OrdersDto ordersDto = new OrdersDto(orderDtos, pageDtos);

        return ordersDto;
    }

    public void create(OrderRegisterDto orderRegisterDto, UserName userName) {
        Order order = new Order(
                orderRegisterDto.getAmount(),
                orderRegisterDto.getTotalPrice(),
                orderRegisterDto.getManufacturer(),
                userName,
                orderRegisterDto.getTitle(),
                orderRegisterDto.getAddress(),
                orderRegisterDto.getReceiver(),
                orderRegisterDto.getMessage(),
                orderRegisterDto.getImage());

        orderRepository.save(order);
    }

    public Order findOrder(Long id) {
        return orderRepository.getReferenceById(id);
    }
}
