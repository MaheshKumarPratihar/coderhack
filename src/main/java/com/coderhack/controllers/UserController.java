package com.coderhack.controllers;

import com.coderhack.exchanges.user.UserRequest;
import com.coderhack.exchanges.user.UserResponse;
import com.coderhack.services.user.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<String> registerUser(
            @RequestBody final @Valid UserRequest request
    ) {
        return ResponseEntity.ok(this.userService.register(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable final String userId
    ) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUserScore(
            @PathVariable final String userId, @RequestParam final int score
    ){
        if(score < 1){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.userService.updateScore(userId, score));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable final String userId){
        return ResponseEntity.ok(this.userService.deleteUser(userId));
    }
}
