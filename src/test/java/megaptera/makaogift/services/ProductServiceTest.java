package megaptera.makaogift.services;

import java.util.List;
import megaptera.makaogift.dtos.ProductsDto;
import megaptera.makaogift.models.Product;
import megaptera.makaogift.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ProductServiceTest {
    private ProductRepository productRepository;
    private ProductService productService;

    @Test
    void productsListWithOnePage() {
        int inputPage = 1;
        Sort sort = Sort.by("CreateAt").descending();
        Pageable pageable = PageRequest.of(inputPage - 1, 8, sort);

        List<Product> products = List.of(
                new Product(1L, "소고기", 5000L),
                new Product(2L, "덤벨", 20000L)
        );

        Page page = new PageImpl(products, pageable, 8);

        productRepository = mock(ProductRepository.class);
        given(productRepository.findAll(pageable))
                .willReturn(page);


        productService = new ProductService(productRepository);

        ProductsDto productsDto = productService.list(inputPage);

        assertThat(productsDto.getProductDtos()).hasSize(2);
        assertThat(page.getTotalPages()).isEqualTo(1);
    }

    @Test
    void productsListTwoPage() {
        int inputPage = 1;
        Sort sort = Sort.by("CreateAt").descending();
        Pageable pageable = PageRequest.of(inputPage - 1, 8, sort);

        List<Product> products = List.of(
                new Product(1L, "소고기", 5000L),
                new Product(2L, "덤벨", 20000L),
                new Product(3L, "소고기", 5000L),
                new Product(4L, "덤벨", 20000L),
                new Product(5L, "소고기", 5000L),
                new Product(6L, "덤벨", 20000L),
                new Product(7L, "소고기", 5000L),
                new Product(8L, "덤벨", 20000L),
                new Product(9L, "소고기", 5000L),
                new Product(10L, "덤벨", 20000L)
        );

        Page<Product> page = new PageImpl<>(products, pageable, 10);

        productRepository = mock(ProductRepository.class);
        given(productRepository.findAll(pageable))
                .willReturn(page);

        productService = new ProductService(productRepository);

        ProductsDto productsDto = productService.list(inputPage);

        assertThat(productsDto.getProductDtos()).hasSize(10);
        assertThat(page.getTotalPages()).isEqualTo(2);
    }
}
