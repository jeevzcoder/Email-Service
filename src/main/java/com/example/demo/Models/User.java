package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
public class User extends BaseModel{
    private String name;
    private String password;
    private String email;
    private  Boolean isEmailVerified;
    @ManyToMany(fetch = FetchType.EAGER)
    private  List<Role> roles;


//
//    private String name;
//    private String email;
//    private String hashedPassword;
//    @ManyToMany(fetch = FetchType.EAGER)
//    private List<Role> roles;
//    private boolean isEmailVerified;

}
