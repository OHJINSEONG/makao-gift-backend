package megaptera.makaogift.controllers;

import megaptera.makaogift.models.UserName;
import megaptera.makaogift.services.OrderService;
import megaptera.makaogift.services.UserService;
import megaptera.makaogift.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrdersContoller.class)
class OrdersContollerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private UserService userService;

    @SpyBean
    private JwtUtil jwtUtil;

    @BeforeEach
    void setup() {
        orderService = mock(OrderService.class);
        userService = mock(UserService.class);
    }

    @Test
    void list() throws Exception {
        String token = jwtUtil.encode(new UserName("ojseong0828"));

        mockMvc.perform(MockMvcRequestBuilders.get("/orders?page=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        String token = jwtUtil.encode(new UserName("ojseong0828"));

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content("{" +
                                "\"amount\": \"2\" , " +
                                "\"totalPrice\": \"20000\" , " +
                                "\"manufacturer\" : \"ㅎㅇ\" ," +
                                "\"title\" : \"ㅎㅇ\"," +
                                "\"receiver\" : \"ㅎㅇ\"," +
                                "\"address\" : \"ㅎㅇ\"," +
                                "\"message\" : \"ㅎㅇ\"," +
                                "\"image\" : \"ㅎㅇ\"" +
                                "}"))
                .andExpect(status().isCreated());
    }
}
