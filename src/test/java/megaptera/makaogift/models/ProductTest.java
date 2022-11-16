package megaptera.makaogift.models;

import megaptera.makaogift.dtos.ProductDto;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
    @Test
    void toDto() {
        Product product = new Product("name", 10000L, "한우", "한국", "맛있음");

        ProductDto productDto = product.toDto();

        assertThat(productDto.getImformation()).isEqualTo("맛있음");
    }
}
