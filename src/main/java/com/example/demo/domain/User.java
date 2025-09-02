package com.example.demo.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User  implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;
    private String fullName;
    private String email;
    private String password;
    private String address;
    private String phone;

    // @Enumerated(EnumType.STRING)
    // @Column(name = "type")
    // private UserType type;
    
    @Override
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "User [ id=" + id + ", address=" + address + ", email=" + email + ", fullName=" + fullName + ",name="
                + username + ", password=" + password + ", phone=" + phone + "]";
    }
    // Default constructor (required by JPA)
    public User() {}

    // Custom constructor (useful for creating a user instance manually)
    public User(String username, String email, String password, String fullName, String address, String phone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User_Role> userRoles = new HashSet<>();  // Change to User_Role

    public Set<Role> getRoles() {
        return userRoles.stream()
                .map(User_Role::getRole)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    // public String getRole() {
    //     return type.name();
    // }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Set<User_Role> getUserRoles() {
        return userRoles;
    }
    public void setUserRoles(Set<User_Role> userRoles) {
        this.userRoles = userRoles;
    }
    
    
}
