package megaptera.makaogift.controllers;

import megaptera.makaogift.dtos.RegisterDto;
import megaptera.makaogift.models.UserName;
import megaptera.makaogift.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public void create(
            @RequestBody RegisterDto registerDto
    ) {
        String name = registerDto.getName();
        UserName userName = new UserName(registerDto.getUserName());
        String password = registerDto.getPassword();
        String reconfirmPassword = registerDto.getReconfirmPassword();

        userService.create(name, userName, password, reconfirmPassword);
    }
}
