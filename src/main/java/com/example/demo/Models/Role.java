package com.example.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="role")
@Getter
@Setter
public class Role extends BaseModel {
    private String role;
   // @ManyToMany
    //private List<User> user;
}
