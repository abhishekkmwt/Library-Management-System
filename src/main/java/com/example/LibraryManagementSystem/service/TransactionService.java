package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dtos.requestDto.TransactionRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.TransactionResponseDto;
import com.example.LibraryManagementSystem.exceptions.BookNotFoundException;
import com.example.LibraryManagementSystem.exceptions.CardNotFoundException;
import jakarta.mail.MessagingException;

public interface TransactionService {

    public TransactionResponseDto transactionOfIssueBook(TransactionRequestDto transactionRequestDto) throws BookNotFoundException, CardNotFoundException, MessagingException;

    public TransactionResponseDto transactionOfReturnBook(TransactionRequestDto transactionRequestDto) throws BookNotFoundException, CardNotFoundException;
}
