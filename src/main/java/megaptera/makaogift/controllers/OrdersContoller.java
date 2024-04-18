package megaptera.makaogift.controllers;

import megaptera.makaogift.dtos.ErrorDto;
import megaptera.makaogift.dtos.OrderRegisterDto;
import megaptera.makaogift.dtos.OrderRegisterErrorDto;
import megaptera.makaogift.dtos.OrderResultDto;
import megaptera.makaogift.dtos.OrdersDto;
import megaptera.makaogift.models.Order;
import megaptera.makaogift.models.UserName;
import megaptera.makaogift.services.OrderService;
import megaptera.makaogift.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            @Validated @RequestBody OrderRegisterDto orderRegisterDto
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
            @PathVariable("id") Long id
    ) {
        Order order = orderService.findOrder(id);
        return order.toResultDto();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto validateError(MethodArgumentNotValidException exception){
        return new OrderRegisterErrorDto(exception.getFieldError().getDefaultMessage());
    }
}qweqweqweqweq
