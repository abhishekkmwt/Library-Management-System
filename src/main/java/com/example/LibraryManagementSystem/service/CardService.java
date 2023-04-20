package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dtos.requestDto.BooksCurrentlyIssuedByCardRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.BooksCurrentlyIssuedByCardResponseDto;
import com.example.LibraryManagementSystem.exceptions.CardNotFoundException;
import org.springframework.web.bind.annotation.RequestParam;

public interface CardService {
    public BooksCurrentlyIssuedByCardResponseDto listOfBooksIssued(BooksCurrentlyIssuedByCardRequestDto booksCurrentlyIssuedByCardRequestDto) throws CardNotFoundException;

    public int noOfBookIssuedCurrentlyToParticularStudent(int id) throws CardNotFoundException;
}
