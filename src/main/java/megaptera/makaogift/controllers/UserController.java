package megaptera.makaogift.controllers;

import megaptera.makaogift.dtos.ErrorDto;
import megaptera.makaogift.dtos.RegisterDto;
import megaptera.makaogift.dtos.SignUpErrorDto;
import megaptera.makaogift.dtos.UserDto;
import megaptera.makaogift.dtos.ValidErrorDto;
import megaptera.makaogift.exceptions.SignUpFailed;
import megaptera.makaogift.models.User;
import megaptera.makaogift.models.UserName;
import megaptera.makaogift.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Validated @RequestBody RegisterDto registerDto
    ) {
        String name = registerDto.getName();
        UserName userName = new UserName(registerDto.getUserName());
        String password = registerDto.getPassword();
        String reconfirmPassword = registerDto.getReconfirmPassword();

        User user = userService.create(name, userName, password, reconfirmPassword);
    }

    @GetMapping
    public UserDto find(
            @RequestAttribute("userName") UserName userName
    ) {
        return userService.find(userName);
    }

    @ExceptionHandler(SignUpFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto signUpFailedException(SignUpFailed signUpFailed) {
        return new SignUpErrorDto(signUpFailed.getCode(), signUpFailed.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto validException(MethodArgumentNotValidException exception) {
        return new ValidErrorDto(exception.getFieldError().getDefaultMessage());
    }
}
