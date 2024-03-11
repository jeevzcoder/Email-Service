package com.example.demo.Security.Models;

import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
public class CustomUserDetails implements UserDetails {
    private User user;
    private Long id;
    private String username;
    private String password;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
   List<CustomGrantedAuthority> authorities=new ArrayList<>();


    public CustomUserDetails() { }
    public CustomUserDetails(User user) {

        this.username = user.getEmail();
        this.password=user.getPassword();
        this.accountNonExpired=true;
        this.accountNonLocked=true;
        this.credentialsNonExpired=true;
        this.enabled=true;
        this.id=user.getId();
        for(Role r : user.getRoles()){
            authorities.add(new CustomGrantedAuthority(r));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;


    }


    public Long getId(){ return id; }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
