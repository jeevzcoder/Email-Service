package com.example.demo.Dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInReqDto {
    private String name;
    private String password;
    private String email;
    private String token;
}
