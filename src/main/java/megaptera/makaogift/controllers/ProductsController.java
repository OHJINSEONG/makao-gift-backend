package megaptera.makaogift.controllers;

import com.querydsl.jpa.impl.JPAQueryFactory;
import megaptera.makaogift.dtos.ProductDto;
import megaptera.makaogift.dtos.ProductsDto;
import megaptera.makaogift.models.Qclass.QProduct;
import megaptera.makaogift.services.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ProductsDto productList(
            @RequestParam("page") Integer page
    ) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        QProduct product = QProduct.product;

        // Construct a QueryDSL query using the Q-class and JPA EntityManager
        queryFactory.selectFrom(product)
                .where(product.price.goe(100000.0)) // Filter products with price greater than or equal to 100
                .orderBy(product.title.asc()) // Sort products by name in ascending order
                .fetch()
                .forEach(p -> System.out.println("Product: " + p.title() + ", Price: " + p.price()));
        entityManager.close();

        return productService.list(page);


    }

    @GetMapping("{id}")
    public ProductDto findProduct(
            @PathVariable("id") Long id
    ) {
        return productService.findProduct(id);
    }
}
