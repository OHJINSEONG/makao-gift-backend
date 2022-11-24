package megaptera.makaogift.dtos;

public class SignUpErrorDto extends ErrorDto {
    public SignUpErrorDto(Integer code, String errorMessage) {
        super(code, errorMessage);
    }
}
