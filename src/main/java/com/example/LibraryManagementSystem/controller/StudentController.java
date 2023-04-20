package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dtos.requestDto.*;
import com.example.LibraryManagementSystem.dtos.responseDto.FindStudentResponseDto;
import com.example.LibraryManagementSystem.dtos.responseDto.UpdateStudentResponseDto;
import com.example.LibraryManagementSystem.exceptions.StudentNotFoundException;
import com.example.LibraryManagementSystem.service.impl.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServiceImp studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto){
        return studentService.addStudent(studentRequestDto);
    }

    @PutMapping("/updateStudentName")
    public UpdateStudentResponseDto updateStudentName(@RequestBody UpdateStudentNameRequestDto updateStudentNameRequestDto) throws StudentNotFoundException {
        return studentService.updateStudentName(updateStudentNameRequestDto);
    }

    @PutMapping("/updateStudentMobile")
    public UpdateStudentResponseDto updateStudentMobile(@RequestBody UpdateStudentMobileRequestDto updateStudentMobileRequestDto) throws StudentNotFoundException {
        return studentService.updateStudentMobile(updateStudentMobileRequestDto);
    }

    @PutMapping("/updateStudentAge")
    public UpdateStudentResponseDto updateStudentMobile(@RequestBody UpdateStudentAgeRequestDto updateStudentAgeRequestDto) throws StudentNotFoundException {
        return studentService.updateStudentMobile(updateStudentAgeRequestDto);
    }

    @DeleteMapping("/deleteById")
    public String deleteById(@RequestParam("id") Integer id){
        return studentService.deleteById(id);
    }

    @GetMapping("/findStudentById")
    public FindStudentResponseDto findStudentById(@RequestBody FindStudentRequestDto findStudentRequestDto) throws StudentNotFoundException {
        return studentService.findStudentById(findStudentRequestDto);
    }

    @GetMapping("/findAllStudents")
    public List<FindStudentResponseDto> findAllStudents(){
        return studentService.findAllStudents();
    }
}
