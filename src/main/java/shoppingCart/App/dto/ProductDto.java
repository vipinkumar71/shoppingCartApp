package shoppingCart.App.dto;

import lombok.Getter;
import shoppingCart.App.entity.Category;

@Getter
public class ProductDto {
    private int productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private boolean stock;
    private int productQuantity;
    private String imageName;
    private CategoryDto category;

    public ProductDto() {
        super();
    }


    public ProductDto(int productId, String productName, String productDescription, double productPrice, boolean stock, int productQuantity, String imageName, CategoryDto category) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.stock = stock;
        this.productQuantity = productQuantity;
        this.imageName = imageName;
        this.category = category;
    }

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
    public void setCategory(CategoryDto category){
        this.category = category;
    }
}
