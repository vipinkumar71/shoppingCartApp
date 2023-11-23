package shoppingCart.App.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingCart.App.entity.User;
import shoppingCart.App.exceptionHandler.ResourceNotFountException;
import shoppingCart.App.repository.UserRepository;
import shoppingCart.App.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    public UserDto createUser(UserDto userDto){
        // userDto to User
        User user = this.mapper.map(userDto, User.class);
        // save
        User saveUser = this.userRepository.save(user);

        // user to UserDto
       UserDto saveUserDto = this.mapper.map(saveUser,UserDto.class);
        return saveUserDto;
    }

    public UserDto getUserById (int userId){
       User user = this.userRepository.findById(userId)
               .orElseThrow(()-> new ResourceNotFountException("User not found by this id " + userId));;
       UserDto userDto = this.mapper.map(user, UserDto.class);
        return userDto;
    }

    public void deleteUserById(int userId){
      User findById =  this.userRepository.findById(userId)
              .orElseThrow(()-> new ResourceNotFountException("User not found by this id " + userId));
            this.userRepository.delete(findById);
    }

    public List<UserDto> findAllUser(){
       List<User> findAllUser = this.userRepository.findAll();
       //User -> userDto
       List<UserDto> collect = findAllUser.stream().map(each -> this.mapper.map(each, UserDto.class)).collect(Collectors.toList());
       return collect;
    }
}
