package megaptera.makaogift.exceptions;

import megaptera.makaogift.models.UserName;

public class LogInFailed extends RuntimeException {
    public LogInFailed(UserName userName) {
        super("User not found (userName: " + userName + " )");
    }

    public LogInFailed(String password) {
        super("Incorrect Password");
    }
}
