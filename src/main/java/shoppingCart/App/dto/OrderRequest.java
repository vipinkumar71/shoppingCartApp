package shoppingCart.App.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private String orderAddress;
    private int cartId;
}