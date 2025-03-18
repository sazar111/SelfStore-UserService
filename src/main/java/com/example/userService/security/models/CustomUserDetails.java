package com.example.userService.security.models;

import com.example.userService.models.Role;
import com.example.userService.models.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private Boolean AccountNonExpired;
    private Boolean AccountNonLocked;
    private Boolean CredentialsNonExpired;
    private Boolean Enabled;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails() {}

    public CustomUserDetails(User user) {
        this.username = user.getEmail();
        this.password= user.getHashedPassword();
        this.AccountNonExpired = true;
        this.AccountNonLocked = true;
        this.CredentialsNonExpired = true;
        this.Enabled = true;
        this.authorities = new ArrayList<>();
        for(Role role : user.getRoles()){
            this.authorities.add(new CustomGrantedAuthority(role));
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return AccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return AccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return CredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return Enabled;
    }
}
