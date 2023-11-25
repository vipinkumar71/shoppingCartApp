package shoppingCart.App.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private UserDto user;
}