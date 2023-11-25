package shoppingCart.App.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shoppingCart.App.entity.User;
import shoppingCart.App.exceptionHandler.ResourceNotFountException;
import shoppingCart.App.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username)
                .orElseThrow(()->new ResourceNotFountException("User Not Found"));
        return user;
    }



}