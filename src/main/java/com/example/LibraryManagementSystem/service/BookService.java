package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dtos.requestDto.AddBookRequestDto;
import com.example.LibraryManagementSystem.dtos.requestDto.NoOfBooksByParticularAuthorRequestDto;
import com.example.LibraryManagementSystem.exceptions.AuthorNotFoundException;

public interface BookService {

    public String addBook(AddBookRequestDto addBookRequestDto) throws AuthorNotFoundException;

    public int noOfBooksByParticularAuthor(NoOfBooksByParticularAuthorRequestDto noOfBooksByParticularAuthorRequestDto) throws AuthorNotFoundException;
}
