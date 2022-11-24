package megaptera.makaogift.dtos;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class OrderRegisterDto {
    private Long amount;

    private Long totalPrice;

    private String manufacturer;

    private String title;

    @NotBlank
    private String receiver;

    @NotBlank
    private String address;

    @Length(max = 100, message = "100글자 이내로 입력해주세요.")
    private String message;

    private String image;

    public OrderRegisterDto(Long amount, Long totalPrice, String manufacturer, String title, String receiver, String address, String message, String image) {
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.manufacturer = manufacturer;
        this.title = title;
        this.receiver = receiver;
        this.address = address;
        this.message = message;
        this.image = image;
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

    public String getImage() {
        return image;
    }
}
