package megaptera.makaogift.controllers;

import megaptera.makaogift.dtos.OrderRegisterDto;
import megaptera.makaogift.dtos.OrderResultDto;
import megaptera.makaogift.dtos.OrdersDto;
import megaptera.makaogift.models.UserName;
import megaptera.makaogift.services.OrderService;
import megaptera.makaogift.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersContoller {
    private final OrderService orderService;
    private final UserService userService;

    public OrdersContoller(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @RequestAttribute("userName") UserName userName,
            @RequestBody OrderRegisterDto orderRegisterDto
    ) {
        userService.order(orderRegisterDto.getTotalPrice(), userName);
        orderService.create(orderRegisterDto, userName);
    }

    @GetMapping
    public OrdersDto list(
            @RequestAttribute("userName") UserName userName,
            @RequestParam("page") Integer page
    ) {
        return orderService.list(userName, page);
    }

    @GetMapping("{id}")
    public OrderResultDto findOrder(
            @RequestParam("id") Long id
    ) {
        return orderService.findOrder(id);
    }
}
