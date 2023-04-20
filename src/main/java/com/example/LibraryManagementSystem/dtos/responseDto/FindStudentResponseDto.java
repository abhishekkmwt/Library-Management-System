package com.example.LibraryManagementSystem.dtos.responseDto;

import com.example.LibraryManagementSystem.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindStudentResponseDto {
    private int id;

    private String name;

    private Department department;

    private int age;

    private String mobNo;

    CardResponseDto cardResponseDto;
}
