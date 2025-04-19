package com.blankk.spring_api.controllers;

import com.blankk.spring_api.dtos.ChangePasswordDTO;
import com.blankk.spring_api.dtos.ChangeUsernameDTO;
import com.blankk.spring_api.dtos.UserCreateDTO;
import com.blankk.spring_api.models.User;
import com.blankk.spring_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User obj = userService.getById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UserCreateDTO obj) {
        User user = new User(obj);
        userService.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping("/changePassword/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordDTO obj) {
        userService.changePassword(id, obj.getPassword());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/changeUsername/{id}")
    public ResponseEntity<Void> updateUsername(@PathVariable Long id, @Valid @RequestBody ChangeUsernameDTO obj) {
        userService.changeUsername(id, obj.getUsername());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
