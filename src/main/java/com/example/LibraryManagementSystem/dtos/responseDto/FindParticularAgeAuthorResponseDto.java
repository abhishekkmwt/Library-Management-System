package com.example.LibraryManagementSystem.dtos.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindParticularAgeAuthorResponseDto {
    private int id;

    private String name;

    private int age;

    private String email;
}
