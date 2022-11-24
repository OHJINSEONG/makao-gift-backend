package megaptera.makaogift.controllers;

import megaptera.makaogift.dtos.ErrorDto;
import megaptera.makaogift.dtos.LoginErrorDto;
import megaptera.makaogift.dtos.LoginRequestDto;
import megaptera.makaogift.dtos.LoginResultDto;
import megaptera.makaogift.exceptions.LogInFailed;
import megaptera.makaogift.models.User;
import megaptera.makaogift.models.UserName;
import megaptera.makaogift.services.LoginService;
import megaptera.makaogift.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    private final LoginService loginService;
    private final JwtUtil jwtUtil;

    public SessionController(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/session")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResultDto login(
            @Validated @RequestBody LoginRequestDto loginRequestDto
    ) {
        UserName userName = new UserName(loginRequestDto.getUserName());
        String password = loginRequestDto.getPassword();

        User user = loginService.login(userName, password);

        String accessToken = jwtUtil.encode(userName);

        return new LoginResultDto(accessToken, user.name(), user.amount());
    }

    @ExceptionHandler(LogInFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto LoginFailed() {
        return new LoginErrorDto();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto loginValidateError(MethodArgumentNotValidException exception) {
        return new LoginErrorDto(exception.getFieldError().getDefaultMessage());
    }
}
