package com.example.demo.Repository;

import com.example.demo.Models.Token;
import com.example.demo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public Optional<User> findUserByEmail(String Email);

   @Override
   public User save(User user);
   Optional<User> findByEmail(String email);
   public Optional<User> findUserByEmailAndPassword(String Email, String Password);
    //public  Optional<User> getUserByEmailAndPassword(String Email, String Password);


    //Optional<User> findUserByEmailAndPassword(String email, String password);


}
