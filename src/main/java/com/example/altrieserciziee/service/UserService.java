package com.example.altrieserciziee.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.altrieserciziee.entities.User;
import com.example.altrieserciziee.exceptions.BadRequestException;
import com.example.altrieserciziee.exceptions.NotFoundException;
import com.example.altrieserciziee.exceptions.UnauthorizedException;
import com.example.altrieserciziee.payloads.NewUserDTO;
import com.example.altrieserciziee.repositories.UserRepository;
import com.example.altrieserciziee.tools.MailgunSender;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private MailgunSender mailgunSender;


    public User save(NewUserDTO body){
        this.userRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("Email has already taken");
                });

       User newUser= new User(body.surname(), body.name(), bcrypt.encode(body.password()), body.email());
//        System.out.println( "password criptata: " + bcrypt.encode(body.password()));
       userRepository.save(newUser);
       mailgunSender.sendRegistrationEmail(newUser);
       return newUser;
    }

    public User findById(UUID userId){
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

//    public User findByEmail(String email){
//        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException( "User with email: " + email + " not found!"));
//    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("User not found with this email"));
    }


    public String uploadAvatar(MultipartFile file) throws IOException {
        return (String) this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
    }

    public User patchAvatarUtente(User user, String url) {
        user.setAvatar(url);
        return userRepository.save(user);
    }

    public User patchUser(UUID utenteId, String name, String surname, String email, String password) {
        User user = userRepository.findById(utenteId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + utenteId));
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);

        return userRepository.save(user);
    }


    public User patchSurnameUtente(User user, String surname){
        user.setSurname(surname);
        return userRepository.save(user);
    }

    public User patchEmailUtente(User user, String email){
        user.setEmail(email);
        return userRepository.save(user);
    }

    public User patchPasswordUtente(User user, String password){
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public List<User> getAll(String sortBy) {
        return this.userRepository.findAll();
    }


}
