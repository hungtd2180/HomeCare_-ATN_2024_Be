package com.example.homecare.controller;

import com.example.homecare.model.dto.CustomerSearchForm;
import com.example.homecare.model.entity.User;
import com.example.homecare.service.UserService;
import com.example.homecare.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(ResourceUtil.PATH.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/new")
    public ResponseEntity<Long> getNewUserId() {
        Long count = userService.countUser();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @PostMapping("/search")
    public ResponseEntity<List<User>> findUser(@RequestBody CustomerSearchForm searchForm){
        searchForm.defaultData();
        List<User> user = userService.findUser(searchForm);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()) {
            user.setUserId(id);
            if (user.getName() != null){
                existingUser.get().setName(user.getName());
            }
            if (user.getEmail() != null){
                existingUser.get().setEmail(user.getEmail());
            }
            if (user.getPhone() != null){
                existingUser.get().setPhone(user.getPhone());
            }
            if (user.getAddress() != null) {
                existingUser.get().setAddress(user.getAddress());
            }
            if (user.getPassword() != null){
                existingUser.get().setPassword(user.getPassword());
            }
            User updatedUser = userService.saveUser(existingUser.get());
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}