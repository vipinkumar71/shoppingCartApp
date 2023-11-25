package shoppingCart.App.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private String orderStatus;
    private String paymentStatus;
    private Date orderDelivered;
    private double orderAmt;
    private String billingAddress;
    private Date orderCreateAt;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private Set<OrderItem> orderItem = new HashSet<>();
}