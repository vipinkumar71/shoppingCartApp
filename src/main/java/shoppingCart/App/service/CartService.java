package shoppingCart.App.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingCart.App.dto.CartDto;
import shoppingCart.App.entity.*;
import shoppingCart.App.exceptionHandler.ResourceNotFountException;
import shoppingCart.App.repository.CartRepository;
import shoppingCart.App.repository.ProductRepository;
import shoppingCart.App.repository.UserRepository;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ModelMapper modelMapper;

    public CartDto addItem(ItemRequest item, String username){
        int productId=item.getProductId();
        int quantity=item.getQuantity();
        //fetch user
        User user = this.userRepository.findByEmail(username)
                .orElseThrow(()->new ResourceNotFountException("User not found"));

        //fetch Product
        Product product=this.productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFountException("Product Not Found"));

        //here we are checking product stock
        if(!product.isStock()){
            throw new ResourceNotFountException("Product Out of Stock");
        }

        // Create cartItem with productId and Quantity
        CartItem cartItem=new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        double totalPrice=product.getProductPrice()*quantity;
        cartItem.setTotalPrice(totalPrice);

        //getting cart from user
        Cart cart = user.getCart();
        if(cart == null) {
            cart =new Cart();
//           cart.setUser(user);
        }
        cartItem.setCart(cart);

        Set<CartItem> items = cart.getItems();

        // Here we check item is available in CartItem or not
        // if CartItem is available then we increase Quantity only else
        // add new product in cartItem


        // by default false
        AtomicReference<Boolean> flag=new AtomicReference<>(false);

        Set<CartItem> newProduct = items.stream().map((i)->{
            if(i.getProduct().getProductId()==product.getProductId()) {
                i.setQuantity(quantity);
                i.setTotalPrice(totalPrice);
                flag.set(true);
            }
            return i;
        }).collect(Collectors.toSet());

        if(flag.get()){
            items.clear();
            items.addAll(newProduct);

        }else {
            cartItem.setCart(cart);
            items.add(cartItem);
        }

        Cart saveCart = this.cartRepository.save(cart);
        return  this.modelMapper.map(saveCart,CartDto.class);
    }


    public CartDto getCartAll(String email){
        //find user
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFountException("User Not Found"));
        //find cart
        Cart cart= this.cartRepository.findByUser(user)
                .orElseThrow(()->new ResourceNotFountException("There is no cart"));
        return this.modelMapper.map(cart,CartDto.class);
    }

    // get cart by CartId
    public CartDto getCartByID(int cartId){

//        User user =this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFountException("User Not found"));

        Cart findByUserAndCartId = this.cartRepository.findById(cartId)
                .orElseThrow(()->new ResourceNotFountException("Cart not Found"));

        return this.modelMapper.map(findByUserAndCartId,CartDto.class);
    }

    public CartDto removeCartItemFromCart(String userName, int productId){
        User user=this.userRepository.findByEmail(userName)
                .orElseThrow(()->new ResourceNotFountException("User Not found"));

        Cart cart=user.getCart();
        Set<CartItem> items = cart.getItems();

        boolean removeIf = items.removeIf((i)->i.getProduct().getProductId() == productId);
        Cart save = this.cartRepository.save(cart);
        return this.modelMapper.map(save,CartDto.class);
    }

}