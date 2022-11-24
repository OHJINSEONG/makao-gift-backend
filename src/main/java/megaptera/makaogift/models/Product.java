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
    private Long price;
    private String title;
    private String manufacturer;
    private String imformation;
    private String image;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Product() {
    }

    public Product(
            Long price,
            String title
            , String manufacturer
            , String imformation,
            String image) {
        this.price = price;
        this.title = title;
        this.manufacturer = manufacturer;
        this.imformation = imformation;
        this.image = image;
    }

    public ProductDto toDto() {
        return new ProductDto(id,
                price,
                title,
                manufacturer,
                imformation,
                image);
    }

    public Long price() {
        return price;
    }

    public String title() {
        return title;
    }

    public String manufacturer() {
        return manufacturer;
    }

    public String imformation() {
        return imformation;
    }

    public String image() {
        return image;
    }
}
