package megaptera.makaogift.dtos;

import megaptera.makaogift.models.UserName;

public class LoginResultDto {
    private String accessToken;
    private UserName userName;
    private String name;
    private Long amount;

    public LoginResultDto(String accessToken, UserName userName, String name, Long amount) {
        this.accessToken = accessToken;
        this.userName = userName;
        this.name = name;
        this.amount = amount;
    }

    public String getAccessToken() {
        return accessToken;
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
