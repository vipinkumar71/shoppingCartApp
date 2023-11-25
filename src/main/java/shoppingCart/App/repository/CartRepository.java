package shoppingCart.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shoppingCart.App.entity.Cart;
import shoppingCart.App.entity.User;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    public Optional<Cart> findByUser(User user);
}