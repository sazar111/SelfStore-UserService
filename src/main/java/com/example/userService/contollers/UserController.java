package com.example.userService.contollers;

import com.example.userService.Projections.UserInfo;
import com.example.userService.dtos.*;
import com.example.userService.models.Token;
import com.example.userService.models.User;
import com.example.userService.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public SignUpDtoResponse signUp(@RequestBody SignUpDto signUpDto){
        User user=userService.signUp(signUpDto
                .getEmail(),signUpDto.getName(),signUpDto.getPassword());
        return user.toSignUpDtoResponse();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        LoginResponseDto token= userService.login(loginDto.getEmail(),loginDto.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody LogoutDto logoutDto){
        userService.logout(logoutDto.getValue());
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/validateToken/{token}")
    public ResponseEntity<LoginResponseDto> validateToken(@PathVariable String token){
        LoginResponseDto tokenResponse= userService.validateToken(token);
        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping("/{id}")
    public String getUserDetails(@PathVariable Long id){
        //TODO
        System.out.println("HEREEEEEEEEEE! "+id);

        return "DONE";
    }
}
