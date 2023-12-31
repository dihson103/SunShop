package com.dinhson.sunshop.appUser;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private Boolean gender;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true)
    private LocalDate dob;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false)
    private Boolean isActive;

//    public User(UserDTO userDTO){
//        name = userDTO.name();
//        email = userDTO.email();
//        password = userDTO.password();
//        enabled = false;
//        isActive = false;
//        role = userDTO.role() == null ? Role.USER : userDTO.role();
//    }
//
//    public User(UserSecurityDTO userDTO){
//        email = userDTO.getEmail();
//        name = userDTO.getName();
//        password = userDTO.getPassword();
//        enabled = false;
//        isActive = false;
//        role = Role.USER;
//    }


}
