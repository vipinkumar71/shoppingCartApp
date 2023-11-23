package shoppingCart.App.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int userId;

    private String name;

    private String email;

    private String password;

    private String address;

    private String gender;

    private String phone;

    private Date date;

}
