package megaptera.makaogift.dtos;

public class OrderDto {
    private Long id;
    private String manufacturer;
    private String title;
    private String receiver;
    private String image;

    public OrderDto(Long id, String manufacturer, String title, String receiver, String image) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.title = title;
        this.receiver = receiver;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getTitle() {
        return title;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getImage() {
        return image;
    }
}
