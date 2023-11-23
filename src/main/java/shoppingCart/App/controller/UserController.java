package shoppingCart.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingCart.App.dto.UserDto;
import shoppingCart.App.service.UserService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        Date date = new Date();
        userDto.setDate(date);
        UserDto createUser = this.userService.createUser(userDto);
        return new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findUserById(@PathVariable int userId){
       UserDto byId = this.userService.getUserById(userId);
        return new ResponseEntity<UserDto>(byId,HttpStatus.FOUND);
    }

    @DeleteMapping("/{userId}")
    void deleteUserById(@PathVariable int userId){
        this.userService.deleteUserById(userId);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> findAllUser = this.userService.findAllUser();
        return new ResponseEntity<List<UserDto>>(findAllUser,HttpStatus.ACCEPTED);
    }
}
