package com.example.LibraryManagementSystem.dtos.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindAuthorResponseDto {
    private int id;

    private String name;

    private int age;

    private String email;

    List<BookResponseDto> bookResponseDto;
}
