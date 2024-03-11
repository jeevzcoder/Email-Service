package com.example.demo.Service;

import com.example.demo.Dtos.SendEmailEventDto;
import com.example.demo.Dtos.SignUpResponseDto;
import com.example.demo.Models.Token;
import com.example.demo.Models.User;
import com.example.demo.Repository.TokenRepo;
import com.example.demo.Repository.UserRepo;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;


@Service
public class UserService {

    UserRepo userRepo;
     BCryptPasswordEncoder passwordEncoder;
     TokenRepo tokenRepo;
     KafkaTemplate<String, String> kafkaTemplate;

     ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepo userRepo,BCryptPasswordEncoder passwordEncoder,TokenRepo tokenRepo,ObjectMapper objectMapper, KafkaTemplate kafkaTemplate) {

        this.userRepo = userRepo;
        this.passwordEncoder=passwordEncoder;
        this.tokenRepo=tokenRepo;
        this.kafkaTemplate=kafkaTemplate;
        this.objectMapper=objectMapper;
    }

    public SignUpResponseDto signUp(String email, String password) throws Exception {

        Optional<User> existingUser= userRepo.findUserByEmail(email);
        if (existingUser.isPresent()){
            throw new Exception("User Already Exists");
        }
        //User user=existingUser.get();
        User user= new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);


        SendEmailEventDto emailEventDto=new SendEmailEventDto();
        emailEventDto.setFrom("");
        emailEventDto.setTo(email);
        emailEventDto.setBody("Smething");
        emailEventDto.setSubject("Sub");

        try{
            kafkaTemplate.send("SendSignUpEmail", objectMapper.writeValueAsString( emailEventDto));
        }
        catch(JacksonException e){
            throw new RuntimeException("JsonParsing Error");
        }



        //kafkaTemplate.



        //KafkaT

        SignUpResponseDto responseDto=new SignUpResponseDto();
        responseDto.setEmail(user.getEmail());
        responseDto.setPassword(passwordEncoder.encode(user.getPassword()));
        responseDto.setResponse("Signup Successful");

        return responseDto;

    }

    public SignUpResponseDto login(String email, String password) throws Exception {
        Optional<User> optUser=userRepo.findUserByEmail(email);
        if(optUser.isEmpty()){
            throw new Exception("Pls signup to proceed");
        }
        User user=  optUser.get();
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new Exception("Wrong Password");
        }
        Token token = getToken(user);
        token.setIsDeleted(Boolean.FALSE);

        // TODO 1: Change the above token to a JWT Token

        Token savedToken = tokenRepo.save(token);
        //return null;

        SignUpResponseDto responseDto=new SignUpResponseDto();
        responseDto.setEmail(user.getEmail());
        responseDto.setPassword(passwordEncoder.encode(user.getPassword()));
        responseDto.setResponse("Signup Successful");
        responseDto.setToken(savedToken.getToken());

        return responseDto;
    }

    private static Token getToken(User user) {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plus(30, ChronoUnit.DAYS);

        // Convert LocalDate to Date
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Token token = new Token();
        token.setUser(user);
        token.setExpiryAt(expiryDate);
        token.setToken(RandomStringUtils.randomAlphanumeric(128));
        return token;
    }






    public SignUpResponseDto logOut(Token token1) throws Exception {
        //return null;

        Optional<Token> optToken=tokenRepo.findByTokenAndIsDeletedEquals(token1.getToken(),false);
        if(optToken.isEmpty()){
            throw new Exception("User not logged in");
        }
        Token token =optToken.get();
        //Boolean True;
        token.setIsDeleted(true);
        tokenRepo.save(token);
        SignUpResponseDto op=new SignUpResponseDto();
        op.setResponse("Signout Done");



        return op;



    }

    public  SignUpResponseDto validate(String token){
        Optional<Token> tkn = tokenRepo.
                findByTokenAndIsDeletedEqualsAndExpiryAtGreaterThan( token, false, new Date());

        if (tkn.isEmpty()) {
            SignUpResponseDto op=new SignUpResponseDto();
            op.setResponse("failure");
            //op.setToken(token.getToken());
            return op;
        }

        SignUpResponseDto op=new SignUpResponseDto();
        op.setResponse("sucess");
        //op.setToken(token.getToken());
        return op;
    }

    }



