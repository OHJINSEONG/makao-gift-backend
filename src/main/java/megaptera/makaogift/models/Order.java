package megaptera.makaogift.models;

import java.time.LocalDateTime;
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
                 String message) {
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.manufacturer = manufacturer;
        this.userName = userName;
        this.title = title;
        this.address = address;
        this.receiver = receiver;
        this.message = message;
    }

    public Long id() {
        return id;
    }

    public UserName userName() {
        return userName;
    }

    public String receiver() {
        return receiver;
    }

    public String message() {
        return message;
    }

    public OrderDto toDto() {
        return new OrderDto(id,
                manufacturer,
                title,
                receiver
        );
    }

    public OrderResultDto toResultDto() {
        String dateOfPurchase = String.valueOf(createdAt);

        return new OrderResultDto(id,
                amount,
                totalPrice,
                manufacturer,
                title,
                receiver,
                message,
                address,
                dateOfPurchase);
    }
}
