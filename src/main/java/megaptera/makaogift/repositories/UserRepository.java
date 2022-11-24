package megaptera.makaogift.repositories;

import java.util.Optional;
import megaptera.makaogift.models.User;
import megaptera.makaogift.models.UserName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(UserName userName);

    User getByUserName(UserName userName);
}
