package shoppingCart.App.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import shoppingCart.App.entity.Cart;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    private String about;
    private String gender;
    private String phone;
    @Column(name="CreateAt")
    private Date date;
    private boolean  active;

    @OneToOne(mappedBy = "user")
    private Cart cart;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles=new HashSet<>();


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public User(int userId, String name, String email, String password, String address, String about, String gender,
                String phone, Date date, boolean active) {
        super();
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.about = about;
        this.gender = gender;
        this.phone = phone;
        this.date = date;
        this.active = active;

    }

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> collect = this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return collect;
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.email;
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}