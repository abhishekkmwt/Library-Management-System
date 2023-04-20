package com.example.LibraryManagementSystem.controller;
import com.example.LibraryManagementSystem.dtos.requestDto.TransactionRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.TransactionResponseDto;
import com.example.LibraryManagementSystem.exceptions.BookNotFoundException;
import com.example.LibraryManagementSystem.exceptions.CardNotFoundException;
import com.example.LibraryManagementSystem.service.TransactionService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;


    @PostMapping("/transactionOfIssueBook")
    public TransactionResponseDto transactionOfIssueBook(@RequestBody TransactionRequestDto transactionRequestDto) throws BookNotFoundException, CardNotFoundException, MessagingException {
        return transactionService.transactionOfIssueBook(transactionRequestDto);
    }

    @PostMapping("/transactionOfReturnBook")
    public TransactionResponseDto transactionOfReturnBook(@RequestBody TransactionRequestDto transactionRequestDto)throws BookNotFoundException, CardNotFoundException {
        return transactionService.transactionOfReturnBook(transactionRequestDto);
    }

}
