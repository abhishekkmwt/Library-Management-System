package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dtos.requestDto.BooksCurrentlyIssuedByCardRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.BooksCurrentlyIssuedByCardResponseDto;
import com.example.LibraryManagementSystem.exceptions.CardNotFoundException;
import com.example.LibraryManagementSystem.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @GetMapping("/listOfBooksIssued")
    public BooksCurrentlyIssuedByCardResponseDto listOfBooksIssued(@RequestBody BooksCurrentlyIssuedByCardRequestDto booksCurrentlyIssuedByCardRequestDto) throws CardNotFoundException {
        return cardService.listOfBooksIssued(booksCurrentlyIssuedByCardRequestDto);
    }

    @GetMapping("/noOfBookIssuedCurrently")
    public int noOfBookIssuedCurrentlyToParticularStudent(@RequestParam("id") Integer id) throws CardNotFoundException {
        return cardService.noOfBookIssuedCurrentlyToParticularStudent(id);
    }
}
