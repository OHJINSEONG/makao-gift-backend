package megaptera.makaogift.interceptor;

import com.auth0.jwt.exceptions.JWTDecodeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import megaptera.makaogift.exceptions.AuthenticationError;
import megaptera.makaogift.models.UserName;
import megaptera.makaogift.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;

    public AuthenticationInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request
            , HttpServletResponse response
            , Object handler) throws Exception {

        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return true;
        }

        String accessToken = authorization.substring("Bearer ".length());

        try {
            UserName userName = jwtUtil.decode(accessToken);

            request.setAttribute("userName", userName);
            return true;
        } catch (JWTDecodeException exception) {
            throw new AuthenticationError();
        }
    }
}
