package com.example.altrieserciziee.controllers;

import com.example.altrieserciziee.entities.User;
import com.example.altrieserciziee.exceptions.BadRequestException;
import com.example.altrieserciziee.payloads.NewUserDTO;
import com.example.altrieserciziee.payloads.NewUserResponseDTO;
import com.example.altrieserciziee.payloads.UserLoginDTO;
import com.example.altrieserciziee.payloads.UserLoginResponseDTO;
import com.example.altrieserciziee.security.JWTTools;
import com.example.altrieserciziee.service.AuthService;
import com.example.altrieserciziee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private JWTTools jwtTools;


    // http://localhost:3001/auth/register

    // POST
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO userResponseDTO (@RequestBody @Validated NewUserDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new NewUserResponseDTO(this.userService.save(body).getId());
    }

    //LOGIN  http://localhost:3001/auth/login
    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody @Validated UserLoginDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        User authenticatedUser = authService.authenticateUserAndGenerateToken(body);
        String token = jwtTools.createToken(authenticatedUser);
        return new UserLoginResponseDTO(token, authenticatedUser.getName(), authenticatedUser.getSurname(), authenticatedUser.getAvatar(), authenticatedUser.getId());
    }


}
