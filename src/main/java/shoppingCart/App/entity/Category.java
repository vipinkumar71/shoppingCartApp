package shoppingCart.App.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String title;
    @Getter
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Product> product;

    public Category() {
        super();
    }
    public Category(int categoryId, String title) {
        this.categoryId = categoryId;
        this.title = title;
    }
}
