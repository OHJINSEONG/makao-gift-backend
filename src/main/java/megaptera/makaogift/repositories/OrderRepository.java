package megaptera.makaogift.repositories;

import megaptera.makaogift.models.Order;
import megaptera.makaogift.models.UserName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByUserName(UserName sender, Pageable pageable);
}
