package shoppingCart.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoppingCart.App.entity.Order;
import shoppingCart.App.entity.User;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
//    public Optional<User>findByEmail(String email);
}
