package megaptera.makaogift.controllers;

import megaptera.makaogift.dtos.ProductDto;
import megaptera.makaogift.dtos.ProductsDto;
import megaptera.makaogift.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ProductsDto productList(
            @RequestParam("page") Integer page
    ) {
        return productService.list(page);
    }

    @GetMapping("{id}")
    public ProductDto findProduct(
            @PathVariable("id") Long id
    ) {
        return productService.findProduct(id);
    }
}
