package com.example.LibraryManagementSystem.dtos.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateStudentMobileRequestDto {

    private int id;

    private String mobNo;
}
