package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dtos.requestDto.AddBookRequestDto;
import com.example.LibraryManagementSystem.dtos.requestDto.NoOfBooksByParticularAuthorRequestDto;
import com.example.LibraryManagementSystem.exceptions.AuthorNotFoundException;
import com.example.LibraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/addBook")
    public String addBook(@RequestBody AddBookRequestDto addBookRequestDto) throws AuthorNotFoundException {
        return bookService.addBook(addBookRequestDto);
    }

    @PostMapping("/noOfBooksByParticularAuthor")
    public int noOfBooksByParticularAuthor(@RequestBody NoOfBooksByParticularAuthorRequestDto noOfBooksByParticularAuthorRequestDto) throws AuthorNotFoundException {
        return bookService.noOfBooksByParticularAuthor(noOfBooksByParticularAuthorRequestDto);
    }
}
