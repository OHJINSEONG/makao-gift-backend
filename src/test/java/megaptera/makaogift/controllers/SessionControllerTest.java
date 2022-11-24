package megaptera.makaogift.controllers;

import megaptera.makaogift.exceptions.LogInFailed;
import megaptera.makaogift.models.User;
import megaptera.makaogift.models.UserName;
import megaptera.makaogift.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.BDDMockito.given;

@WebMvcTest(SessionController.class)
class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @BeforeEach
    void setup() {
        UserName userName = new UserName("ojseong0828");
        String password = "Wlstjdcjs153!";
        String wrongPassword = "wrongPassword";

        User user = new User(1L, userName, "오진성", 50000L);

        given(loginService.login(userName, password))
                .willReturn(user);

        given(loginService.login(userName, wrongPassword))
                .willThrow(new LogInFailed(wrongPassword));
    }

    @Test
    void loginSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"userName\":\"ojseong0828\"" +
                                ",\"password\":\"Wlstjdcjs153!\"" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void loginFailed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"userName\":\"ojseong0828\"" +
                                ",\"password\":\"wrongPassword\"" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
