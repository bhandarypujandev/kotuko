package com.kotuko.kotuko.dto;

import com.kotuko.kotuko.entity.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;

    public UserResponse(User user) {
        this.id = user.getId();
        this.dateOfBirth=user.getDateOfBirth();
        this.username=user.getUsername();
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.email=user.getEmail();
    }
}
