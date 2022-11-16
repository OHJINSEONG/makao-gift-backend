package megaptera.makaogift.dtos;

public class ProductDto {
    private Long id;
    private String name;
    private Long price;
    private String title;
    private String manufacturer;
    private String imformation;

    public ProductDto(Long id,
                      String name,
                      Long price,
                      String title,
                      String manufacturer,
                      String imformation) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.title = title;
        this.manufacturer = manufacturer;
        this.imformation = imformation;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
}
