package megaptera.makaogift.dtos;

public class ProductDto {
    private Long id;
    private Long price;
    private String title;
    private String manufacturer;
    private String imformation;
    private String image;

    public ProductDto(Long id,
                      Long price,
                      String title,
                      String manufacturer,
                      String imformation,
                      String image) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.manufacturer = manufacturer;
        this.imformation = imformation;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public Long getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getImformation() {
        return imformation;
    }

    public String getImage() {
        return image;
    }
}
