package shoppingCart.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoppingCart.App.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
