package megaptera.makaogift.controllers;

import megaptera.makaogift.dtos.LoginRequestDto;
import megaptera.makaogift.dtos.LoginResultDto;
import megaptera.makaogift.models.User;
import megaptera.makaogift.models.UserName;
import megaptera.makaogift.services.LoginService;
import megaptera.makaogift.utils.JwtUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public LoginResultDto session(
            @Validated @RequestBody LoginRequestDto loginRequestDto
    ) {
        UserName userName = new UserName(loginRequestDto.getUserName());
        String password = loginRequestDto.getPassword();

        User user = loginService.login(userName, password);

        String accessToken = jwtUtil.encode(userName);

        return new LoginResultDto(accessToken, user.userName(), user.name(), user.amount());
    }
}
