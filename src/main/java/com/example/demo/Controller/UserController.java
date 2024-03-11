package com.example.demo.Controller;

import com.example.demo.Dtos.SignInReqDto;
import com.example.demo.Dtos.SignUpResponseDto;
import com.example.demo.Models.Token;
import com.example.demo.Models.User;
import com.example.demo.Service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@Controller
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/signup")
    public SignUpResponseDto SignUp(@RequestBody SignInReqDto reqDto  ) throws Exception {
        return userService.signUp(reqDto.getEmail(),reqDto.getPassword());
    }

    @PostMapping("/login")
    public SignUpResponseDto login(@RequestBody SignInReqDto reqDto  ) throws Exception {
        return userService.login(reqDto.getEmail(),reqDto.getPassword());
    }

    @PostMapping("/logout")
    public SignUpResponseDto logOut(@RequestBody Token token ) throws Exception {
        return userService.logOut(token);

    }

    @PostMapping("/validate/{token}")
    public  SignUpResponseDto validateUser(@PathVariable("token") @RequestBody String token){
        //ResponseEntity<SignUpResponseDto> response=new ResponseEntity<>(ACECPTED);
        return userService.validate(token);

    }





}
