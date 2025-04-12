package com.ToDoApp.ToDoApp.Controllers;


import com.ToDoApp.ToDoApp.Responses.RegistrationResponseDTO;
import com.ToDoApp.ToDoApp.Responses.UsersResponseDTO;
import com.ToDoApp.ToDoApp.Services.UserService;
import com.ToDoApp.ToDoApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="api/v1/auth")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;

    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest){
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        User user = userService.authenticateUser(username, password);

        if (user != null) {
            return ResponseEntity.ok().body("Login successful. Welcome" + " " +username);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDTO> register(@RequestBody User user ){

        User createdUser = userService.register(user);

        if (createdUser != null) {
            createdUser.setPassword(null);
            String message = "Account Created Successfully!";
            RegistrationResponseDTO response = new RegistrationResponseDTO(message, createdUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UsersResponseDTO>> getUsers() {
        List<User> users = userService.getAllUsers();
        List<UsersResponseDTO> usersDTO = users.stream()
                .map(UsersResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usersDTO);
    }


    @GetMapping("/users/{id}")
    public void getUser(){

    }
    @PutMapping("/update/{id}")
    public void updateUser(){

    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(){

    }

}
