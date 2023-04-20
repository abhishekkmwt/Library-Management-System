package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dtos.requestDto.AddAuthorRequestDto;
import com.example.LibraryManagementSystem.dtos.requestDto.FindAllAuthorsWithParticularAgeRequestDto;
import com.example.LibraryManagementSystem.dtos.requestDto.FindAuthorByEmailRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.FindAuthorResponseDto;
import com.example.LibraryManagementSystem.dtos.responseDto.FindParticularAgeAuthorResponseDto;
import com.example.LibraryManagementSystem.exceptions.AuthorNotFoundException;
import com.example.LibraryManagementSystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/addAuthor")
    public String addAuthor(@RequestBody AddAuthorRequestDto addAuthorRequestDto){
        return authorService.addAuthor(addAuthorRequestDto);
    }

    @DeleteMapping("/deleteAuthorById")
    public String deleteAuthorById(@RequestParam("id") Integer id){
        return authorService.deleteAuthorById(id);
    }

    @GetMapping("/findAuthorByEmail")
    public FindAuthorResponseDto findAuthorByEmail(@RequestBody FindAuthorByEmailRequestDto findAuthorByEmailRequestDto) throws AuthorNotFoundException {
        return authorService.findAuthorByEmail(findAuthorByEmailRequestDto);
    }

    @GetMapping("/allAuthorsOfParticularAge")
    public List<FindParticularAgeAuthorResponseDto> allAuthorsOfParticularAge(@RequestBody FindAllAuthorsWithParticularAgeRequestDto findAllAuthorsWithParticularAgeRequestDto){
        return authorService.allAuthorsOfParticularAge(findAllAuthorsWithParticularAgeRequestDto);
    }


}
