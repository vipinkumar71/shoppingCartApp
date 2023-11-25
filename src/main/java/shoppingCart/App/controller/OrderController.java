package shoppingCart.App.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingCart.App.dto.ApiResponse;
import shoppingCart.App.dto.OrderDto;
import shoppingCart.App.dto.OrderRequest;
import shoppingCart.App.dto.OrderResponse;
import shoppingCart.App.service.OrderService;

import java.security.Principal;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    //create order
    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest orderRequest, Principal principal) {
        String username=principal.getName();
        OrderDto order=this.orderService.orderCreate(orderRequest,username);
        return new ResponseEntity<OrderDto>(order, HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> cancelOrderById(@PathVariable int orderId){
        this.orderService.CancelOrder(orderId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Order deleted",true),HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto>findById(@PathVariable int orderId){
        OrderDto orderDto = this.orderService.findById(orderId);
        return new ResponseEntity<OrderDto>(orderDto,HttpStatus.ACCEPTED);
    }


    @GetMapping("/getAll")
    public OrderResponse findAllOrders(
            @RequestParam(defaultValue = "2",value = "pageSize") int pageSize
            ,@RequestParam(defaultValue = "0" ,value ="pageNumber" ) int pageNumber
    )

    {
        OrderResponse findAllOrders = this.orderService.findAllOrders(pageNumber, pageSize);
        return findAllOrders;
    }
}
