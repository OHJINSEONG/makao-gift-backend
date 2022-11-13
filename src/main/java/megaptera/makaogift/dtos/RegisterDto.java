package megaptera.makaogift.dtos;

public class RegisterDto {
    private String name;
    private String userName;
    private String password;
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
