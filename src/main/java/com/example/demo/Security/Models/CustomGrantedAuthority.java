package com.example.demo.Security.Models;

import com.example.demo.Models.Role;
import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.extern.jackson.Jacksonized;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {
    Role role;
    String authority;

    public CustomGrantedAuthority() { }
    public CustomGrantedAuthority(Role role) {

        this.authority =role.getRole();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
