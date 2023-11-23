package shoppingCart.App.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.Date;


@Entity
@Table(name ="user_info")
@Getter
@Setter
@AllArgsConstructor
@FieldNameConstants
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String address;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false, length = 10)
    private String phone;

    @Column(name = "CreateAt")
    private Date date;
}
