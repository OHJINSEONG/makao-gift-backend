package megaptera.makaogift.dtos;

public class LoginErrorDto extends ErrorDto {
    public LoginErrorDto() {
        super(1002, "아이디 혹은 비밀번호가 맞지 않습니다.");
    }

    public LoginErrorDto(String defaultMessage) {
        super(1002, defaultMessage);
    }
}
