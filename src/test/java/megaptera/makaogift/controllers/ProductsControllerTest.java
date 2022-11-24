package megaptera.makaogift.controllers;

import megaptera.makaogift.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductsController.class)
class ProductsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void productList() throws Exception {
        productService = mock(ProductService.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/products?page=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void findProduct() throws Exception {
        productService = mock(ProductService.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isOk());
    }
}
