package com.ToDoApp.ToDoApp.Controllers;


import com.ToDoApp.ToDoApp.Responses.RegistrationResponseDTO;
import com.ToDoApp.ToDoApp.Responses.UserResponseDTO;
import com.ToDoApp.ToDoApp.Responses.UsersResponseDTO;
import com.ToDoApp.ToDoApp.Services.UserService;
import com.ToDoApp.ToDoApp.models.User;
import org.hibernate.query.UnknownSqlResultSetMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="api/v1")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;

    }


    @PostMapping("/auth/login")
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

    @PostMapping("/auth/register")
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
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        Optional<User> userOptional = userService.getUserById(id);

        if (userOptional.isPresent()){
            String successMessage = "User of Id " + id + " retrieved Successfully!";
            User user = userOptional.get();
            user.setPassword(null);

        // Create UserResponseDTO with the user and success message
            UserResponseDTO responseDTO = new UserResponseDTO(successMessage, userOptional.get());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }else{
        String failMessage = "User not Found";
            UserResponseDTO responseDTO = new UserResponseDTO(failMessage,null);
            return new ResponseEntity<>(responseDTO,HttpStatus.NOT_FOUND);
        }

    }

//  Upon Testing: Only put the updated fields. An error
    @PutMapping("/users/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        String result = userService.updateUser(id, updatedUser);
        if (result.equals("User updated successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        String result = userService.deleteUser(id);

        if (result.equals("User deleted successfully")){

            return result;
        }else{

            return "User not found";
        }

    }

}
