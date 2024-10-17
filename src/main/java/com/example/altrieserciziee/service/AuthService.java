package com.example.altrieserciziee.service;

import com.example.altrieserciziee.entities.User;
import com.example.altrieserciziee.exceptions.UnauthorizedException;
import com.example.altrieserciziee.payloads.UserLoginDTO;
import com.example.altrieserciziee.security.JWTTools;
import com.example.altrieserciziee.tools.MailgunSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private JWTTools jwtTools;


//    public User authenticateUserAndGenerateToken(UserLoginDTO payload){
//        User found = userService.findByEmail(payload.email());
//        if (bcrypt.matches(payload.password(), found.getPassword())){
//           return found;
//       } else {
//           System.out.println( "payload.password: " + payload.password() + "found.password: " + found.getPassword());
//           throw new UnauthorizedException("Wrong Credentials!");
//       }
//
//    }

    public User authenticateUserAndGenerateToken(UserLoginDTO payload) {
        User found = userService.findByEmail(payload.email());
        System.out.println("Retrieved user: " + found);
        if (found == null) {
            throw new UnauthorizedException("User not found with this email!");
        }
        System.out.println("Found user password: " + found.getPassword());

        if (bcrypt.matches(payload.password(), found.getPassword())) {
            return found;
        } else {
            throw new UnauthorizedException("Wrong Credentials!");
        }
    }


}
