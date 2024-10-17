package com.example.altrieserciziee.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(value = { "id",  "authorities", "enabled", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"  })
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private UUID id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String avatar;
    private Boolean isAdmin;

    public User( String surname, String name, String password, String email) {
        this.isAdmin = true;
        this.avatar = "https://ui-avatars.com/api/?name=" + this.name + "+" + this.surname;
        this.surname = surname;
        this.name = name;
        this.password = password;
        this.email = email;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
