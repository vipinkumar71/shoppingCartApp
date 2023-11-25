package shoppingCart.App.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {

    private int orderItemId;
    private ProductDto product;
    private double totalProductPrice;
    @JsonIgnore
    private OrderDto order;
}