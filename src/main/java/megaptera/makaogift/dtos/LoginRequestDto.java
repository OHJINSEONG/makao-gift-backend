package megaptera.makaogift.dtos;

import javax.validation.constraints.NotBlank;

public class LoginRequestDto {
    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    public LoginRequestDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
