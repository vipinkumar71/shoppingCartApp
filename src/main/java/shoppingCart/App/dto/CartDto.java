package shoppingCart.App.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
public class CartDto {
    private int cartId;
    private Set<CartItemDto> items= new HashSet<>();
    private UserDto user;
}