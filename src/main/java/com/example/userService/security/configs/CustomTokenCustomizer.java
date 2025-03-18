package com.example.userService.security.configs;

import com.example.userService.security.models.CustomGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {
    @Override
    public void customize(JwtEncodingContext context) {
        if (context.getTokenType().getValue().equals("access_token")) {
            Object principal = context.getPrincipal().getPrincipal();

            if (principal instanceof UserDetails userDetails) {
                // Extract roles from UserDetails

                List<String> roles = userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());

                // Add roles to JWT claims
                context.getClaims().claim("roles", roles);
            }
        }
    }
}
