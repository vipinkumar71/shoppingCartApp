package shoppingCart.App.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Product> getProduct(Set<Product> product) {
        return product;
    }
    public void setProduct(Set<Product> product) {
        this.product = product;
    }
}
