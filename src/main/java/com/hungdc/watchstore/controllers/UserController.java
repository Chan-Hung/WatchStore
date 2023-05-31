package com.hungdc.watchstore.controllers;

import com.hungdc.watchstore.dtos.user.UserDto;
import com.hungdc.watchstore.entities.User;
import com.hungdc.watchstore.services.template.CreateUserApi;
import com.hungdc.watchstore.services.template.DeleteUserApi;
import com.hungdc.watchstore.services.template.UpdateUserApi;
import com.hungdc.watchstore.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @Autowired
    private CreateUserApi createUserApi;
    @Autowired
    private UpdateUserApi updateUserApi;
    @Autowired
    private DeleteUserApi deleteUserApi;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        return new ResponseEntity<>(userService.getTaiKhoan(id), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<User> create(@Valid @RequestBody UserDto dto) {
//        return new ResponseEntity<>(userService.create(dto), HttpStatus.OK);
//    }
    @PostMapping
    public User create(@Valid @RequestBody UserDto dto) {
        return createUserApi.execute(null,dto);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{id}")
//    public ResponseEntity<User> update(@PathVariable String id, @Valid @RequestBody UserDto dto) {
//        return new ResponseEntity<>(userService.update(id, dto), HttpStatus.OK);
//    }
    public User update(@PathVariable String id, @Valid @RequestBody UserDto dto) {
        return updateUserApi.execute(id, dto);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/{id}")
//    public ResponseEntity<User> delete(@PathVariable String id) {
//        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
//    }
    public User delete(@PathVariable String id) {
        return deleteUserApi.execute(id,null);
    }
}
