package shoppingCart.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoppingCart.App.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
