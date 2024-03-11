package com.example.demo.Repository;

import com.example.demo.Models.Role;
import com.example.demo.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository< Token, Long> {
    public Token save(Token token);
    public Optional<Token> findByTokenAndIsDeletedEquals(String value, boolean isDeleted);
    public Optional<Token> findByTokenAndIsDeletedEqualsAndExpiryAtGreaterThan(String value,Boolean bool,Date date);// false, new Date());

    //Token indByValueAndDeletedEquals(String taahusWrMHV5UJdPDxVnl5hDgVKDNbhwETH1nsH5HKbpL3xzQ6hAQ9gAjmhsa0hAhIVvc2ECZpMXrnlBaw97O2OQKsosCtojSuGGMoPCx2yOwcbV0HnecrEiPpVRq7xg, Boolean aFalse);
    //Optional<Token> findByValueAndDeletedEqualsAndExpiryAtGreaterThan(String value, boolean isDeleted, Date expiryGreaterThan);
}



