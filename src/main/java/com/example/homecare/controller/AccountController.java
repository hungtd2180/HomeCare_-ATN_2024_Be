package com.example.homecare.controller;

import com.example.homecare.model.dto.LoginObject;
import com.example.homecare.model.entity.Collaborator;
import com.example.homecare.model.entity.CollaboratorAccount;
import com.example.homecare.model.entity.User;
import com.example.homecare.service.CollaboratorAccountService;
import com.example.homecare.service.CollaboratorService;
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
@RequestMapping
public class AccountController {
    @Autowired
    UserService userService;
    @Autowired
    CollaboratorService collaboratorService;
    @Autowired
    CollaboratorAccountService collaboratorAccountService;

    @PostMapping("/register/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User registerUser = userService.saveUser(user);
            return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/register/collaborator")
    public ResponseEntity<?> createCollaborator(@RequestBody Collaborator collaborator) {
        try {
            Collaborator registerCollaborator = collaboratorService.saveCollaborator(collaborator);
            return new ResponseEntity<>(registerCollaborator, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("login/user")
    public ResponseEntity<?> loginUser(@RequestBody LoginObject login) {
        Optional<User> loginUser = userService.loginUser(login.getUsername(), login.getPassword());
        if (loginUser.isPresent()) {
            return new ResponseEntity<>(loginUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("401", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("login/collaborator")
    public ResponseEntity<?> loginCollaborator(@RequestBody LoginObject login) {
        Optional<CollaboratorAccount> collaboratorAccount = collaboratorAccountService.getCollaboratorAccountByUsernameandPassword(login.getUsername(), login.getPassword());
        Optional<Collaborator> collaborator = collaboratorService.getCollaboratorById(collaboratorAccount.get().getCollaborator().getCollaboratorId());
        if (collaboratorAccount.isPresent()) {
            if (collaborator.get().getStatus() == 0) {
                return new ResponseEntity<>("403", HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(collaborator.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("401", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/register/new")
    public ResponseEntity<?> registerNew(@RequestBody LoginObject login) {
        try {
            CollaboratorAccount register = collaboratorAccountService.saveCollaboratorAccount(login.getUsername(), login.getPassword(), login.getCollaboratorId());
            return new ResponseEntity<>(register, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/register/{id}")
    public ResponseEntity<List<CollaboratorAccount>> getRegistered(@PathVariable String id) {
        try {
            List<CollaboratorAccount> list = collaboratorAccountService.getAllByCollaboratorId(id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/register/{id}")
    public ResponseEntity<HttpStatus> deleteRegistered(@PathVariable String id) {
        try {
            collaboratorAccountService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
