package megaptera.makaogift.models;

import java.time.LocalDateTime;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import megaptera.makaogift.dtos.UserDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

@Table(name = "PERSON")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private UserName userName;

    private String name;

    private Long amount;

    private String encodedPassword;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User() {
    }

    public User(Long id, UserName userName, String name, Long amount) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.amount = amount;
    }

    @Embedded
    public UserName userName() {
        return userName;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Long amount() {
        return amount;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public boolean authenticate(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.encodedPassword);
    }

    public UserDto toDto() {
        return new UserDto(name, amount);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.encodedPassword = passwordEncoder.encode(password);
    }

    public void order(Long totalPrice) {
        this.amount -= totalPrice;
    }
}
