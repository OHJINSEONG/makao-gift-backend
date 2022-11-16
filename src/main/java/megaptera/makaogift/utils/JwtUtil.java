package megaptera.makaogift.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import megaptera.makaogift.models.UserName;

public class JwtUtil {
    private final Algorithm algorithm;

    public JwtUtil(String secret) {
        this.algorithm = Algorithm.HMAC256(secret);
    }


    public String encode(UserName userName) {
        String token = JWT.create()
                .withClaim("userName", userName.value())
                .sign(algorithm);

        return token;
    }

    public UserName decode(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT verify = verifier.verify(token);
        String value = verify.getClaim("userName").asString();
        return new UserName(value);
    }
}
