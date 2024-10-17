package com.example.altrieserciziee.controllers;

import com.example.altrieserciziee.entities.User;
import com.example.altrieserciziee.exceptions.BadRequestException;
import com.example.altrieserciziee.exceptions.NotFoundException;
import com.example.altrieserciziee.payloads.NewUserDTO;
import com.example.altrieserciziee.payloads.UpdateAvatarDTO;
import com.example.altrieserciziee.payloads.UpdateAvatarResponseDTO;
import com.example.altrieserciziee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UserController {
    @Autowired
    private UserService userService;
//    @Autowired
//    private PasswordEncoder bcrypt;

    @GetMapping
    public List<User> getAll(@RequestParam(defaultValue = "id") String sortBy){
        return  this.userService.getAll(sortBy);
    }

    @PutMapping("/{utenteId}/profile")
    public ResponseEntity<User> patchUser(@PathVariable UUID utenteId,
                                          @RequestBody @Validated NewUserDTO body,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }

        User updatedUser = userService.patchUser(
                utenteId,
                body.name(),
                body.surname(),
                body.email(),
                body.password()


        );

        return ResponseEntity.ok(updatedUser);
    }


    @PutMapping("/{userId}/avatar")
    public ResponseEntity<UpdateAvatarResponseDTO> updateAvatar(@PathVariable UUID userId, @RequestBody UpdateAvatarDTO request) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new NotFoundException("User with id " + userId + " not found!");
        }


        User updatedUser = userService.patchAvatarUtente(user, request.url());
        String updatedAvatarUrl = request.url();

        return ResponseEntity.ok(new UpdateAvatarResponseDTO(updatedAvatarUrl));
    }



}