package org.example.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {
    @NotEmpty
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]+\s[A-ZА-Я][a-zа-я]+")
    String name;

    @NotEmpty
    @Email
    String email;

    @Positive
    int age;
}
