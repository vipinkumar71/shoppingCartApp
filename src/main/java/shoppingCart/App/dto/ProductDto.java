package shoppingCart.App.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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
    public void setCategory(CategoryDto categoryDto){
        this.category = categoryDto;
    }
}
