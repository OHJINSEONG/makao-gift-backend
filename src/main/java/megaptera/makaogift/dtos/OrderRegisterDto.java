package megaptera.makaogift.dtos;

public class OrderRegisterDto {
    private Long amount;
    private Long totalPrice;
    private String manufacturer;
    private String title;
    private String receiver;
    private String address;
    private String message;

    public OrderRegisterDto(Long amount, Long totalPrice, String manufacturer, String title, String receiver, String address, String message) {
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.manufacturer = manufacturer;
        this.title = title;
        this.receiver = receiver;
        this.address = address;
        this.message = message;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getTotalPrice() {
        return totalPrice;
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

    public String getAddress() {
        return address;
    }

    public String getMessage() {
        return message;
    }
}
