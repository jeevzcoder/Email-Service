package com.example.demo.Security.Service;

import com.example.demo.Models.User;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Security.Models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    UserRepo userRepo;
    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> u =userRepo.findUserByEmail(username);
        if (u.isEmpty()){
            throw new UsernameNotFoundException("Pls signup to login");
        }
        return new CustomUserDetails(u.get());
        }
    }

