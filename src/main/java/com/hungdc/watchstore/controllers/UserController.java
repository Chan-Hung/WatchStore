package com.hungdc.watchstore.controllers;

import com.hungdc.watchstore.dtos.user.UserDto;
import com.hungdc.watchstore.entities.User;
import com.hungdc.watchstore.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        return new ResponseEntity<>(userService.getTaiKhoan(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserDto dto) {
        return new ResponseEntity<>(userService.create(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @Valid @RequestBody UserDto dto) {
        return new ResponseEntity<>(userService.update(id, dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable String id) {
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }
}
