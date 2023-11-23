package shoppingCart.App.entity;

import jakarta.persistence.*;
import lombok.Getter;
import shoppingCart.App.dto.CategoryDto;

@Getter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    private String productName;
    private String productDescription;
    private double productPrice;

    private boolean stock;

    private int productQuantity;

    private String imageName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
