package megaptera.makaogift.utils;

import megaptera.makaogift.models.UserName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
    @Test
    void encode(){
        JwtUtil jwtUtil = new JwtUtil("SECRET");

        UserName userName = new UserName("userName");

        String accessToken = jwtUtil.encode(userName);

        UserName decodedUserName = jwtUtil.decode(accessToken);

        assertThat(decodedUserName).isEqualTo(userName);
    }
}
