package megaptera.makaogift.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import megaptera.makaogift.dtos.OrderDto;
import megaptera.makaogift.dtos.OrderResultDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "GIFT")
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private Long amount;

    @Column(name = "TOTALPRICE")
    private Long totalPrice;

    private String manufacturer;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "sender"))
    private UserName userName;

    private String title;

    private String address;

    private String receiver;

    private String message;

    private String image;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Order() {
    }

    public Order(Long amount,
                 Long totalPrice,
                 String manufacturer,
                 UserName userName,
                 String title,
                 String address,
                 String receiver,
                 String message, String image) {
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.manufacturer = manufacturer;
        this.userName = userName;
        this.title = title;
        this.address = address;
        this.receiver = receiver;
        this.message = message;
        this.image = image;
    }

       public OrderDto toDto() {
        return new OrderDto(id,
                manufacturer,
                title,
                receiver,
                image);
    }

    public OrderResultDto toResultDto() {
        String dateOfPurchase = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return new OrderResultDto(id,
                amount,
                totalPrice,
                manufacturer,
                title,
                receiver,
                message,
                address,
                dateOfPurchase, image);
    }

    public Long amount() {
        return amount;
    }

    public Long totalPrice() {
        return totalPrice;
    }

    public String manufacturer() {
        return manufacturer;
    }

    public String title() {
        return title;
    }

    public String address() {
        return address;
    }

    public String receiver() {
        return receiver;
    }

    public String message() {
        return message;
    }

    public String image() {
        return image;
    }
}
