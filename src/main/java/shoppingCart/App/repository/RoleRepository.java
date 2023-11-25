package shoppingCart.App.repository;

import org.springframework.stereotype.Repository;
import shoppingCart.App.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
