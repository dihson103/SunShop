package com.dinhson.sunshop.appUser;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
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
    private Date dob;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false)
    private Boolean isActive;

}
