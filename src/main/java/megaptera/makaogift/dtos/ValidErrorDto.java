package megaptera.makaogift.dtos;

public class ValidErrorDto extends ErrorDto {
    public ValidErrorDto(String errorMessage) {
        super(2000, errorMessage);
    }
}
