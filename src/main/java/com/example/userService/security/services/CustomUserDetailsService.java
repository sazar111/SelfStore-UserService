package com.example.userService.security.services;

import com.example.userService.models.Role;
import com.example.userService.models.User;
import com.example.userService.repositories.UserRepository;
import com.example.userService.security.models.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> Optionaluser = userRepository.findByEmail(username);
        Optionaluser.orElseThrow(() -> new UsernameNotFoundException(username));
        User user= Optionaluser.get();

        return new CustomUserDetails(user);
    }

    private Set<GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getValue()))
                .collect(Collectors.toSet());
    }
}
