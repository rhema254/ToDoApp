package com.ToDoApp.ToDoApp.Responses;

import com.ToDoApp.ToDoApp.Enums.Role;
import com.ToDoApp.ToDoApp.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersResponseDTO {

    private Long id;
    private String username;
    private String email;
    private Role role;

    public UsersResponseDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}

