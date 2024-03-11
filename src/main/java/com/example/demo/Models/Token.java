package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="token")
@Getter
@Setter
public class Token extends BaseModel{
    private String token;
    private Date expiryAt;
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="users",nullable = false)
    private User user;
    private Boolean isDeleted;

}
