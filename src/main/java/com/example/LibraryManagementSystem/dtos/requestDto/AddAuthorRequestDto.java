package com.example.LibraryManagementSystem.dtos.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddAuthorRequestDto {
    private String name;

    private int age;

    private String email;
}
