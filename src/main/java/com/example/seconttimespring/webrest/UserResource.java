package com.example.seconttimespring.webrest;

import com.example.seconttimespring.entity.User;
import com.example.seconttimespring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/api")
public class UserResource implements Serializable {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user){
        if(userService.existsByLogin(user.getLogin())){
            return new ResponseEntity("bu login movjud", HttpStatus.BAD_REQUEST);
        }

        if (checkPasswordLength(user.getPassword())){
            return new ResponseEntity("password uzunligi 4 tadan kam", HttpStatus.BAD_REQUEST);
        }
        User result = userService.save(user);
        return ResponseEntity.ok(result);
    }

    private boolean checkPasswordLength(String password){
        return password.length() <= 4;
    }
}
