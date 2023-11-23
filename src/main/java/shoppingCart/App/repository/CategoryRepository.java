package shoppingCart.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoppingCart.App.entity.Category;
@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer> {
}
