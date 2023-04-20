package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dtos.requestDto.*;
import com.example.LibraryManagementSystem.dtos.responseDto.FindStudentResponseDto;
import com.example.LibraryManagementSystem.dtos.responseDto.UpdateStudentResponseDto;
import com.example.LibraryManagementSystem.exceptions.StudentNotFoundException;


import java.util.List;


public interface StudentService {
    public String addStudent(StudentRequestDto studentRequestDto);

    public UpdateStudentResponseDto updateStudentName(UpdateStudentNameRequestDto updateStudentNameRequestDto) throws StudentNotFoundException;
    public UpdateStudentResponseDto updateStudentMobile(UpdateStudentMobileRequestDto updateStudentMobileRequestDto) throws StudentNotFoundException;
    public UpdateStudentResponseDto updateStudentMobile(UpdateStudentAgeRequestDto updateStudentAgeRequestDto) throws StudentNotFoundException;
    public String deleteById(int id);
    public FindStudentResponseDto findStudentById(FindStudentRequestDto findStudentRequestDto) throws StudentNotFoundException;
    public List<FindStudentResponseDto> findAllStudents();
}
