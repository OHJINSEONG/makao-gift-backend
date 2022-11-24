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
public class HomeContoller {
    @GetMapping("/")
    public String home() {
        return "hi";
    }
}
