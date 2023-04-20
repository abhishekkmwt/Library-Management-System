package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dtos.requestDto.AddAuthorRequestDto;
import com.example.LibraryManagementSystem.dtos.requestDto.FindAllAuthorsWithParticularAgeRequestDto;
import com.example.LibraryManagementSystem.dtos.requestDto.FindAuthorByEmailRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.FindAuthorResponseDto;
import com.example.LibraryManagementSystem.dtos.responseDto.FindParticularAgeAuthorResponseDto;
import com.example.LibraryManagementSystem.exceptions.AuthorNotFoundException;

import java.util.List;

public interface AuthorService {

    public String addAuthor(AddAuthorRequestDto addAuthorRequestDto);

    public String deleteAuthorById(int id);

    public FindAuthorResponseDto findAuthorByEmail(FindAuthorByEmailRequestDto findAuthorByEmailRequestDto) throws AuthorNotFoundException;

    public List<FindParticularAgeAuthorResponseDto> allAuthorsOfParticularAge(FindAllAuthorsWithParticularAgeRequestDto findAllAuthorsWithParticularAgeRequestDto);

}
