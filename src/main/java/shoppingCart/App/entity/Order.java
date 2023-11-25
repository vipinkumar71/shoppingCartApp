//package shoppingCart.App.entity;
//
//import lombok.Data;
//import lombok.Getter;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//
//@Getter
//@Entity(name = "Orders")
//public class Order {
//    //Getter && Setter
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int orderId;
//    private String orderStatus;
//    private String paymentStatus;
//    private Date orderDelivered;
//    private double orderAmt;
//    private String billingAddress;
//    private Date orderCreateAt;
//    @OneToOne
//    private User user;
//    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
//    private Set<OrderItem> orderItem=new HashSet<>();
//    public Order() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//    public Order(int orderId, String orderStatus, String paymentStatus, Date orderDelivered, double orderAmt,
//                 String billingAddress, Date orderCreateAt, User user, Set<OrderItem> orderItem) {
//        super();
//        this.orderId = orderId;
//        this.orderStatus = orderStatus;
//        this.paymentStatus = paymentStatus;
//        this.orderDelivered = orderDelivered;
//        this.orderAmt = orderAmt;
//        this.billingAddress = billingAddress;
//        this.orderCreateAt = orderCreateAt;
//        this.user = user;
//        this.orderItem = orderItem;
//    }
//
//    public void setOrderCreateAt(Date orderCreateAt) {
//        this.orderCreateAt = orderCreateAt;
//    }
//
//    public void setOrderId(int orderId) {
//        this.orderId = orderId;
//    }
//
//    public void setOrderStatus(String orderStatus) {
//        this.orderStatus = orderStatus;
//    }
//
//    public void setPaymentStatus(String paymentStatus) {
//        this.paymentStatus = paymentStatus;
//    }
//
//    public void setOrderDelivered(Date orderDelivered) {
//        this.orderDelivered = orderDelivered;
//    }
//
//    public void setOrderAmt(double orderAmt) {
//        this.orderAmt = orderAmt;
//    }
//
//    public void setBillingAddress(String billingAddress) {
//        this.billingAddress = billingAddress;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public void setOrderItem(Set<OrderItem> orderItem) {
//        this.orderItem = orderItem;
//    }
//
//}