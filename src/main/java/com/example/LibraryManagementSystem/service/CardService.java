package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dtos.requestDto.BooksCurrentlyIssuedByCardRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.BooksCurrentlyIssuedByCardResponseDto;
import com.example.LibraryManagementSystem.exceptions.CardNotFoundException;

public interface CardService {
    public BooksCurrentlyIssuedByCardResponseDto listOfBooksIssued(BooksCurrentlyIssuedByCardRequestDto booksCurrentlyIssuedByCardRequestDto) throws CardNotFoundException;
}