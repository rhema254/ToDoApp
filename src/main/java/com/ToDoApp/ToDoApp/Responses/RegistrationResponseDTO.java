package com.ToDoApp.ToDoApp.Responses;

import com.ToDoApp.ToDoApp.models.User;


public class RegistrationResponseDTO {

    private String message;
    private User user;


    public RegistrationResponseDTO(String message, User user) {
        this.message = message;
        this.user = user;
    }

    // Getters and setters
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
