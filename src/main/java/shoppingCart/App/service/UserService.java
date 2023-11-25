package shoppingCart.App.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shoppingCart.App.dto.UserDto;
import shoppingCart.App.entity.User;
import shoppingCart.App.exceptionHandler.ResourceNotFountException;
import shoppingCart.App.repository.RoleRepository;
import shoppingCart.App.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class  UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    public UserDto createUser(UserDto userDto) {
        User user=this.toEntity(userDto);
        String passEncode = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(passEncode);
        User userCreate=this.userRepository.save(user);
        return this.toDto(userCreate);
    }

    public UserDto update(UserDto t, int userId) {
        User u=userRepository.findById(userId).orElseThrow(()->new ResourceNotFountException("User not found by this id"));
        u.setPhone(t.getPhone());
        u.setPassword(t.getPassword());
        u.setName(t.getName());
        u.setGender(t.getGender());
        u.setEmail(t.getEmail());
        u.setDate(t.getDate());
        u.setAddress(t.getAddress());
        u.setActive(t.isActive());
        u.setAbout(t.getAbout());
        User Updateuser=this.userRepository.save(u);
        return this.toDto(Updateuser);
    }


    public void deleteUserById(int userId) {
        User u=userRepository.findById(userId).orElseThrow(() ->new ResourceNotFountException("UserId not Found"));
        userRepository.delete(u);

    }


    public List<UserDto> getAll() {
        List<User> allUser=userRepository.findAll();
        List<UserDto> allUserDto=allUser.stream().map(user -> this.toDto(user)).collect(Collectors.toList());

        return allUserDto;
    }


    public UserDto getUserById(int userId) {
        User finduser=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFountException("User Not Found"+userId));

        return this.toDto(finduser);
    }


    public UserDto getByEmailId(String emailId) {
        User findEmail=userRepository.findByEmail(emailId).orElseThrow(() -> new ResourceNotFountException("User Email Is is Not Exit"+emailId));
        return this.toDto(findEmail);
    }

    public UserDto toDto(User u) {
//		UserDto dto=new UserDto();
//		dto.setUserId(u.getUserId());
//		dto.setName(u.getName());
//		dto.setEmail(u.getEmail());
//		dto.setPassword(u.getPassword());
//		dto.setAbout(u.getAbout());
//		dto.setAddress(u.getAddress());
//		dto.setGender(u.getGender());
//		dto.setDate(u.getDate());
//		dto.setPhone(u.getPhone());
//		dto.setActive(u.isActive());
        //dto.setRoles(u.getRoles());
        //return dto;

        return this.mapper.map(u,UserDto.class);
    }

    public User toEntity(UserDto dto) {
//	User  u=new User();
//	u.setUserId(dto.getUserId());
//	u.setPhone(dto.getPhone());
//	u.setPassword(dto.getPassword());
//	u.setName(dto.getName());
//	u.setGender(dto.getGender());
//	u.setEmail(dto.getEmail());
//	u.setDate(dto.getDate());
//	u.setAddress(dto.getAddress());
//	u.setActive(dto.isActive());
//	u.setAbout(dto.getAbout());
//	//u.setRoles(u.getRoles());
//	return u;
        return this.mapper.map(dto,User.class);
    }
}