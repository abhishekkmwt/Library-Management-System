package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.Entity.Card;
import com.example.LibraryManagementSystem.Entity.Student;
import com.example.LibraryManagementSystem.dtos.requestDto.*;
import com.example.LibraryManagementSystem.dtos.responseDto.CardResponseDto;
import com.example.LibraryManagementSystem.dtos.responseDto.FindStudentResponseDto;
import com.example.LibraryManagementSystem.dtos.responseDto.UpdateStudentResponseDto;
import com.example.LibraryManagementSystem.enums.Status;
import com.example.LibraryManagementSystem.exceptions.StudentNotFoundException;
import com.example.LibraryManagementSystem.repository.StudentRepository;
import com.example.LibraryManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public String addStudent(StudentRequestDto studentRequestDto) {

        Student student=new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setMobNo(studentRequestDto.getMobNo());
        student.setEmail(studentRequestDto.getEmail());

        Card card=new Card();
        card.setStatus(Status.ACTIVE);
        card.setExpiryDate("2024-01-01");
        card.setStudent(student);
        student.setCard(card);

        studentRepository.save(student);
        return "Student Added";
    }

    public UpdateStudentResponseDto updateStudentName(UpdateStudentNameRequestDto updateStudentNameRequestDto) throws StudentNotFoundException {
        try{
            Student student=studentRepository.findById(updateStudentNameRequestDto.getId()).get();
            UpdateStudentResponseDto updateStudentResponseDto =new UpdateStudentResponseDto();
            updateStudentResponseDto.setName(updateStudentNameRequestDto.getName());
            updateStudentResponseDto.setMobNo(student.getMobNo());
            updateStudentResponseDto.setAge(student.getAge());
            student.setName(updateStudentNameRequestDto.getName());
            studentRepository.save(student);
            return updateStudentResponseDto;
        }
        catch (Exception e){
            throw new StudentNotFoundException("Invalid Student id");
        }
    }

    public UpdateStudentResponseDto updateStudentMobile(UpdateStudentMobileRequestDto updateStudentMobileRequestDto) throws StudentNotFoundException {
        try{
            Student student=studentRepository.findById(updateStudentMobileRequestDto.getId()).get();
            UpdateStudentResponseDto updateStudentResponseDto =new UpdateStudentResponseDto();
            updateStudentResponseDto.setName(student.getName());
            updateStudentResponseDto.setMobNo(updateStudentMobileRequestDto.getMobNo());
            updateStudentResponseDto.setAge(student.getAge());
            student.setMobNo(updateStudentMobileRequestDto.getMobNo());
            studentRepository.save(student);
            return updateStudentResponseDto;
        }
        catch (Exception e){
            throw new StudentNotFoundException("Invalid Student id");
        }
    }

    public UpdateStudentResponseDto updateStudentMobile(UpdateStudentAgeRequestDto updateStudentAgeRequestDto) throws StudentNotFoundException{
        try{
            Student student=studentRepository.findById(updateStudentAgeRequestDto.getId()).get();
            UpdateStudentResponseDto updateStudentResponseDto =new UpdateStudentResponseDto();
            updateStudentResponseDto.setName(student.getName());
            updateStudentResponseDto.setMobNo(student.getMobNo());
            updateStudentResponseDto.setAge(updateStudentAgeRequestDto.getAge());
            student.setAge(updateStudentAgeRequestDto.getAge());
            studentRepository.save(student);
            return updateStudentResponseDto;
        }
        catch (Exception e){
            throw new StudentNotFoundException("Invalid Student id");
        }
    }

    public String deleteById(int id){
        studentRepository.deleteById(id);
        return "Deleted Successfully.";
    }

    public FindStudentResponseDto findStudentById(FindStudentRequestDto findStudentRequestDto) throws StudentNotFoundException {
        try{
            Student student= studentRepository.findById(findStudentRequestDto.getId()).get();

            FindStudentResponseDto findStudentResponseDto=new FindStudentResponseDto();
            findStudentResponseDto.setAge(student.getAge());
            findStudentResponseDto.setId(student.getId());
            findStudentResponseDto.setDepartment(student.getDepartment());
            findStudentResponseDto.setName(student.getName());
            findStudentResponseDto.setMobNo(student.getMobNo());

            CardResponseDto cardResponseDto=new CardResponseDto();
            cardResponseDto.setId(student.getCard().getId());
            cardResponseDto.setStatus(student.getCard().getStatus());
            cardResponseDto.setExpiryDate(student.getCard().getExpiryDate());
            cardResponseDto.setIssueDate(student.getCard().getIssueDate());

            findStudentResponseDto.setCardResponseDto(cardResponseDto);

            return findStudentResponseDto;
        }
        catch(Exception e){
            throw new StudentNotFoundException("Student Not Found");
        }

    }

    public List<FindStudentResponseDto> findAllStudents(){

        List<Student> students = studentRepository.findAll();
        List<FindStudentResponseDto> list =new ArrayList<>();
        for(Student s : students){
            FindStudentResponseDto findStudentResponseDto=new FindStudentResponseDto();
            findStudentResponseDto.setAge(s.getAge());
            findStudentResponseDto.setId(s.getId());
            findStudentResponseDto.setDepartment(s.getDepartment());
            findStudentResponseDto.setName(s.getName());
            findStudentResponseDto.setMobNo(s.getMobNo());

            CardResponseDto cardResponseDto=new CardResponseDto();
            cardResponseDto.setId(s.getCard().getId());
            cardResponseDto.setStatus(s.getCard().getStatus());
            cardResponseDto.setExpiryDate(s.getCard().getExpiryDate());
            cardResponseDto.setIssueDate(s.getCard().getIssueDate());

            findStudentResponseDto.setCardResponseDto(cardResponseDto);

            list.add(findStudentResponseDto);
        }
        return list;
    }


}
