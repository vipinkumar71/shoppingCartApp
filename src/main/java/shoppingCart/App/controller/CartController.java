package shoppingCart.App.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingCart.App.dto.CartDto;
import shoppingCart.App.service.CartService;
import shoppingCart.App.entity.ItemRequest;
import java.security.Principal;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/add")
    public ResponseEntity<CartDto> addToCart(@RequestBody ItemRequest itemRequest, Principal principal) {
        CartDto addItem = this.cartService.addItem(itemRequest, principal.getName());
        return new ResponseEntity<CartDto>(addItem, HttpStatus.OK);
    }


    //create method for getting cart
    @GetMapping("/allCart")
    public ResponseEntity<CartDto> getAllCart(Principal principal) {
        CartDto getCartAll = this.cartService.getCartAll(principal.getName());
        return new ResponseEntity<CartDto>(getCartAll, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartById(@PathVariable int cartId) {
        CartDto cartByID = this.cartService.getCartByID(cartId);
        return new ResponseEntity<CartDto>(cartByID, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<CartDto> deleteCartItemFromCart(@PathVariable int productId, Principal p) {
        CartDto remove = this.cartService.removeCartItemFromCart(p.getName(), productId);
        return new ResponseEntity<CartDto>(remove, HttpStatus.UPGRADE_REQUIRED);
    }
}