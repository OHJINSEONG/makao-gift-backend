package megaptera.makaogift.models;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import megaptera.makaogift.dtos.ProductDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long price;
    private String title;
    private String manufacturer;
    private String imformation;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Product() {
    }

    public Product(String name
            , Long price, String title
            , String manufacturer
            , String imformation) {
        this.name = name;
        this.price = price;
        this.title = title;
        this.manufacturer = manufacturer;
        this.imformation = imformation;
    }

    public ProductDto toDto() {
        return new ProductDto(id,
                name,
                price,
                title,
                manufacturer,
                imformation);
    }
}
