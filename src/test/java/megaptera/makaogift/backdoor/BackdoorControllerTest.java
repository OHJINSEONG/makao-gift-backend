package megaptera.makaogift.backdoor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class BackdoorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void setupDatabase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/backdoor/setup-database"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/backdoor/delete-products"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteOrders() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/backdoor/delete-orders"))
                .andExpect(status().isOk());
    }
}
