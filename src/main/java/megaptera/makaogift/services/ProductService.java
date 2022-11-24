package megaptera.makaogift.services;

import java.util.ArrayList;
import java.util.List;
import megaptera.makaogift.dtos.PageDto;
import megaptera.makaogift.dtos.ProductDto;
import megaptera.makaogift.dtos.ProductsDto;
import megaptera.makaogift.models.Product;
import megaptera.makaogift.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductsDto list(int page) {
        Sort sort = Sort.by("id").descending();

        Pageable pageable = PageRequest.of(page - 1, 8, sort);

        Page<Product> products = productRepository.findAll(pageable);

        List<ProductDto> productDtos = products.stream().map(Product::toDto).toList();

        List<PageDto> pageDtos = new ArrayList<>();
        for (int i = 1; i <= products.getTotalPages(); i += 1) {
            PageDto pageDto = new PageDto(i);
            pageDtos.add(pageDto);
        }

        ProductsDto productsDto = new ProductsDto(productDtos, pageDtos);

        return productsDto;
    }

    public ProductDto findProduct(Long id) {
        Product product = productRepository.getReferenceById(id);
        return product.toDto();
    }
}
