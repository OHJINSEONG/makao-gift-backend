package megaptera.makaogift.controllers;

import megaptera.makaogift.exceptions.SignUpFailed;
import megaptera.makaogift.models.User;
import megaptera.makaogift.models.UserName;
import megaptera.makaogift.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setup() {
        String name = "오진성";
        UserName userName = new UserName("ojs0828");
        String password = "Wlstjdcjs153!";
        String reconfirmPassword = "Wlstjdcjs153!";

        UserName existedUserName = new UserName("ojseong0828");
        String wrongReconfirmPassword = "Wlstjdcjs153!!";

        User user = new User(null, userName, name, 50000L);

        given(userService.create(name, userName, password, reconfirmPassword))
                .willReturn(user);

        given(userService.create(name, existedUserName, password, reconfirmPassword))
                .willThrow(new SignUpFailed(existedUserName));

        given(userService.create(name, existedUserName, password, wrongReconfirmPassword))
                .willThrow(new SignUpFailed(wrongReconfirmPassword));
    }


    @Test
    void createSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"오진성\"," +
                                "\"userName\":\"ojs0828\"," +
                                "\"password\":\"Wlstjdcjs153!\"," +
                                "\"reconfirmPassword\":\"Wlstjdcjs153!\"" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void createWithBlankNameError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"\"," +
                                "\"userName\":\"ojseong0828\"," +
                                "\"password\":\"Wlstjdcjs153!\"," +
                                "\"reconfirmPassword\":\"Wlstjdcjs153!\"" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(
                        containsString("2000")));
    }

    @Test
    void createWithBlankUserNameError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"오진성\"," +
                                "\"userName\":\"\"," +
                                "\"password\":\"Wlstjdcjs153!\"," +
                                "\"reconfirmPassword\":\"Wlstjdcjs153!\"" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(
                        containsString("2000")));
    }

    @Test
    void createWithBlankPasswordError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"오진성\"," +
                                "\"userName\":\"ojseong0828\"," +
                                "\"password\":\"\"," +
                                "\"reconfirmPassword\":\"Wlstjdcjs153!\"" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(
                        containsString("2000")));
    }

    @Test
    void createWithBlankReconfirmPasswordError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"오진성\"," +
                                "\"userName\":\"ojseong0828\"," +
                                "\"password\":\"Wlstjdcjs153!\"," +
                                "\"reconfirmPassword\":\"\"" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(
                        containsString("2000")));
    }

    @Test
    void createExistUserNameError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"오진성\"," +
                                "\"userName\":\"ojseong0828\"," +
                                "\"password\":\"Wlstjdcjs153!\"," +
                                "\"reconfirmPassword\":\"Wlstjdcjs153!\"" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(
                        containsString("1001")));
    }

    @Test
    void createReconfirmError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"오진성\"," +
                                "\"userName\":\"ojseong0828\"," +
                                "\"password\":\"Wlstjdcjs153!\"," +
                                "\"reconfirmPassword\":\"Wlstjdcjs153!!\"" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(
                        containsString("1000")));
    }
}
