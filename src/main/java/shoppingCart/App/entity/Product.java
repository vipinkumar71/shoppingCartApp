package shoppingCart.App.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String productName;
    private String productDescription;
    private double productPrice;

    private boolean stock;

    private int productQuantity;

    private String imageName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
