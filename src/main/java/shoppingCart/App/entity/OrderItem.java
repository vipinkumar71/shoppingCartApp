//package shoppingCart.App.entity;
//
//import lombok.Getter;
//
//import javax.persistence.*;
//
//@Getter
//@Entity
//public class OrderItem {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int orderItemId;
//    @OneToOne
//    private Product product;
//
//    private double totalProductPrice;
//
//    private int productQuantity;
//    @ManyToOne
//    private Order order;
//
//
//    public void setProductQuantity(int productQuantity) {
//        this.productQuantity = productQuantity;
//    }
//    public OrderItem(int orderItemId, Product product, double totalProductPrice, int productQuantity, Order order) {
//        super();
//        this.orderItemId = orderItemId;
//        this.product = product;
//        this.totalProductPrice = totalProductPrice;
//        this.productQuantity = productQuantity;
//        this.order = order;
//    }
//    public OrderItem() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//    public void setOrderItemId(int orderItemId) {
//        this.orderItemId = orderItemId;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public void setTotalProductPrice(double totalProductPrice) {
//        this.totalProductPrice = totalProductPrice;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }
//
//
//
//}
