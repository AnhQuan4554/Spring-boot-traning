package com.example.java_training.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
public class UserRequest {
    @NotNull(message = "Name cannot empty")
    private String name;
    @PositiveOrZero(message = "Age greater than or equal to 0")
    private int old;
    @Email
    private String email;
    private String password;
}
