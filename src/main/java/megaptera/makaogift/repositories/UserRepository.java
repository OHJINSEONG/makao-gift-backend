package megaptera.makaogift.repositories;

import java.util.Optional;
import megaptera.makaogift.models.User;
import megaptera.makaogift.models.UserName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(UserName userName);
}
