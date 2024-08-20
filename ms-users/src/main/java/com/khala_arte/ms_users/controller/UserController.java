package com.khala_arte.ms_users.controller;

import com.khala_arte.ms_users.dto.UserDTO;
import com.khala_arte.ms_users.service.implementations.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        ResponseEntity response = null;
        Optional<UserDTO> userDTO = userService.getUserById(id);
        if (userDTO.isPresent()) {
            response = new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id of: " + id + " not found");
        }
        return response;
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        ResponseEntity response;
        Optional<UserDTO> userDTO = userService.getUserByEmail(email);
        if (userDTO.isPresent()) {
            response = new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with the email: " + email + " not found");
        }
        return response;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers() {
        ResponseEntity response = null;
        Set<UserDTO> userDTOS = userService.getAllUsers();
        if (userDTOS.isEmpty()) {
            response = new ResponseEntity<>("No users found", HttpStatus.NO_CONTENT);
        }
        else {
            response = new ResponseEntity<>(userDTOS, HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO newUserDTO = userService.addUser(userDTO);
            return new ResponseEntity<>("User created successfully - " + newUserDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating the user - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUserDTO = userService.updateUser(userDTO);
            return new ResponseEntity<>("User updated successfully - " + updatedUserDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating the user - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>("User with id of: " + id + " deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting the user - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
