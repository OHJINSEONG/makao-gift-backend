package megaptera.makaogift.exceptions;

public class AuthenticationError extends RuntimeException {
    public AuthenticationError() {
        super("위험: 해킹시도!");
    }
}
