package com.example.userService.services;

import com.example.userService.dtos.LoginResponseDto;
import com.example.userService.models.BaseClass;
import com.example.userService.models.Token;
import com.example.userService.models.User;
import com.example.userService.repositories.TokenRepository;
import com.example.userService.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    TokenRepository tokenRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    public User signUp(String email,String name, String password) {

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        User savedUser= (User) userRepository.save(user);
        return savedUser;
    }

    public LoginResponseDto login(String email, String password) {
        Token token = new Token();
        Optional<User> optionalUser= userRepository.findByEmail(email);
        User user;
        LoginResponseDto responseToken = new LoginResponseDto();

        user = optionalUser.orElse(null);
        if (user == null || !bCryptPasswordEncoder.matches(password, user.getHashedPassword())) {
            return null;
        }
        Optional<Token> optionalToken= tokenRepository.findByUserAndDeleted(user,true);
        if(optionalToken.isPresent()) {
            return responseToken.fromToken(optionalToken.get());
        }

        String randomToken= RandomStringUtils.randomAlphanumeric(128);
        Date date= new Date();
        Date expiryDate = new Date(date.getTime()+24*60*60*1000);
        token.setValue(randomToken);
        token.setUser(user);
        token.setExpiryDate(expiryDate);
        Token repositoryToken= tokenRepository.save(token);

        return responseToken.fromToken(repositoryToken);
    }

    public Boolean logout(String value) {
        Optional<Token> OptionalToken= tokenRepository.findByValueAndDeleted(value,false);
        Token token;
        if(OptionalToken.isPresent()) {
            token = OptionalToken.get();
        }else{
            return false;
        }
        token.setDeleted(true);
        Token tokenDeleted= tokenRepository.save(token);
        return tokenDeleted!=null;
    }

    public LoginResponseDto validateToken(String tokenToCheck){
/*        Optional<User> optionalUser= userRepository.findByToken(tokenToCheck);
        User user= optionalUser.orElse(null);

        if(optionalUser.isPresent()) {
            user= optionalUser.get();
        }*/
        LoginResponseDto responseToken = new LoginResponseDto();
        Optional<Token> token= tokenRepository.findByValue(tokenToCheck);
        if(token.isPresent()) {
            return responseToken.fromToken(token.get());
        }else{
            return null;
        }
    }
}
