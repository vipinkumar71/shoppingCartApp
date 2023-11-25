package shoppingCart.App.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shoppingCart.App.dto.OrderDto;
import shoppingCart.App.dto.OrderRequest;
import shoppingCart.App.dto.OrderResponse;
import shoppingCart.App.entity.*;
import shoppingCart.App.exceptionHandler.ResourceNotFountException;
import shoppingCart.App.repository.CartRepository;
import shoppingCart.App.repository.OrderRepository;
import shoppingCart.App.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ModelMapper modelMapper;

    //order Create method
    public OrderDto orderCreate(OrderRequest request, String username) {

        User user = this.userRepository.findByEmail(username)
                .orElseThrow(()->new ResourceNotFountException("User not found"));

        int cartId=request.getCartId();
        String orderAddress=request.getOrderAddress();
        //find cart
        Cart cart= this.cartRepository.findById(cartId)
                .orElseThrow(()->new ResourceNotFountException("Cart Not Found"));

        //getting CartItem
        Set<CartItem> items  = cart.getItems();
        Order order=new Order();

        AtomicReference<Double> totalOrderPrice= new AtomicReference<Double>(0.0);
        Set<OrderItem>	orderItems=items.stream().map((cartItem)-> {
            OrderItem orderItem=new OrderItem();
            //set cartItem into OrderItem

            //set product in orderItem
            orderItem.setProduct(cartItem.getProduct());
            //set productQty in orderItem
            orderItem.setProductQuantity(cartItem.getQuantity());
            orderItem.setTotalProductPrice(cartItem.getTotalPrice());
            orderItem.setOrder(order);

            totalOrderPrice.set(totalOrderPrice.get()  + orderItem.getTotalProductPrice());
            int productId = orderItem.getProduct().getProductId();

            return orderItem;
        }).collect(Collectors.toSet());

        order.setBillingAddress(orderAddress);
        order.setOrderDelivered(null);
        order.setOrderStatus("CREATED");
        order.setPaymentStatus("NOT PAID");
        order.setUser(user);
        order.setOrderItem(orderItems);
        order.setOrderAmt(totalOrderPrice.get());
        order.setOrderCreateAt(new Date());
        Order save;
        if(order.getOrderAmt()>0){
            save = this.orderRepository.save(order);
            cart.getItems().clear();
            this.cartRepository.save(cart);
        }else {
            throw new ResourceNotFountException("Please Add Cart First and place Order");
        }
        return this.modelMapper.map(save,OrderDto.class);
    }

    public void CancelOrder(int orderId){
        Order order = this.orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFountException("Order not Found"));
        this.orderRepository.delete(order);
    }


    public OrderDto findById(int orderId){
        Order order = this.orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFountException("order not found"));
        return this.modelMapper.map(order,OrderDto.class);
    }

    // find All Product by page
    public OrderResponse findAllOrders(int pageNumber, int pageSize ){
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        Page<Order> findAll=this.orderRepository.findAll(pageable);
        List<Order> content = findAll.getContent();

        //change order to orderDto
        List<OrderDto> collect = content.stream().map((each)->this.modelMapper.map(each,OrderDto.class)).collect(Collectors.toList());
        OrderResponse response= new OrderResponse();
        response.setContent(collect);
        response.setPageNumber(findAll.getNumber());
        response.setLastPage(findAll.isLast());
        response.setPageSize(findAll.getSize());
        response.setTotalPage(findAll.getTotalPages());

        //getTotalElements return Long
        response.setTotalElement(findAll.getTotalElements());
        return response;
    }
}