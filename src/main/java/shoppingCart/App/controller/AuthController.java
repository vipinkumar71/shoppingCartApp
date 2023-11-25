package shoppingCart.App.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shoppingCart.App.Security.JwtHelper;
import shoppingCart.App.dto.JwtRequest;
import shoppingCart.App.dto.JwtResponse;
import shoppingCart.App.dto.UserDto;
import shoppingCart.App.exceptionHandler.ResourceNotFountException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserDetailsService userDetailService;
    @Autowired
    private JwtHelper helper;
    @Autowired
    private ModelMapper model;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.authenticateUser(request.getUsername(), request.getPassword());
        UserDetails userDetails = this.userDetailService.loadUserByUsername(request.getUsername());
        String token=this.helper.generateToken(userDetails);
        JwtResponse response=new JwtResponse();
        response.setToken(token);
        response.setUser(this.model.map(userDetails, UserDto.class));
        return new ResponseEntity<JwtResponse>(response, HttpStatus.ACCEPTED);
    }

    private void authenticateUser(String Username ,String password){
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(Username, password));
        }catch(BadCredentialsException e) {
            throw new ResourceNotFountException("Invalid username or password");
        }catch(DisabledException e) {
            throw new ResourceNotFountException("User is not active");
        }

    }

}