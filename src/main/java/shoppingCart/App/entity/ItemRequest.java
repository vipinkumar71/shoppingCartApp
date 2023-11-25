package shoppingCart.App.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequest {
    private int productId;
    private int quantity;
}