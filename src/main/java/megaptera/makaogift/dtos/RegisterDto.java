package megaptera.makaogift.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class RegisterDto {
    @NotBlank(message = "이름을 입력해주세요.")
    @Length(min = 3, max = 7, message = "이름을 다시 확인해주세요.")
    private String name;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9]{4,16}$", message = "아이디를 다시 확인해주세요.")
    private String userName;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}"
            , message = "비밀번호를 다시 확인해주세요.")
    private String password;

    @NotBlank(message = "비밀번호확인을 입력해주세요.")
    private String reconfirmPassword;

    public RegisterDto(String name, String userName, String password, String reconfirmPassword) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.reconfirmPassword = reconfirmPassword;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getReconfirmPassword() {
        return reconfirmPassword;
    }
}
