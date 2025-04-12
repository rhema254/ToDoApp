package com.ToDoApp.ToDoApp.Responses;

import com.ToDoApp.ToDoApp.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;


public class UserResponseDTO {

    private String message;
    private User user;


    public UserResponseDTO(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


