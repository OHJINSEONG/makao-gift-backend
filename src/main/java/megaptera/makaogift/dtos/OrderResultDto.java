package megaptera.makaogift.dtos;

public class OrderResultDto {
    private Long id;
    private Long amount;
    private Long totalPrice;
    private String manufacturer;
    private String title;
    private String receiver;
    private String message;
    private String address;
    private String dateOfPurchase;

    public OrderResultDto(Long id,
                          Long amount,
                          Long totalPrice,
                          String manufacturer,
                          String title,
                          String receiver,
                          String message,
                          String address,
                          String dateOfPurchase) {
        this.id = id;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.manufacturer = manufacturer;
        this.title = title;
        this.receiver = receiver;
        this.message = message;
        this.address = address;
        this.dateOfPurchase = dateOfPurchase;
    }

    public Long getId() {
        return id;
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

    public String getMessage() {
        return message;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }
}
