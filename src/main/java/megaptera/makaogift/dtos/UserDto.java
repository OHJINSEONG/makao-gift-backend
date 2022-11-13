package megaptera.makaogift.dtos;

import megaptera.makaogift.models.UserName;

public class UserDto {
    private UserName userName;
    private String name;
    private Long amount;

    public UserDto(UserName userName, String name, Long amount) {
        this.userName = userName;
        this.name = name;
        this.amount = amount;
    }

    public UserName getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public Long getAmount() {
        return amount;
    }
}
