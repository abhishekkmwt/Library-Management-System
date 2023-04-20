package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Entity.Card;
import com.example.LibraryManagementSystem.Entity.Transaction;
import com.example.LibraryManagementSystem.dtos.requestDto.TransactionRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.TransactionResponseDto;
import com.example.LibraryManagementSystem.enums.Status;
import com.example.LibraryManagementSystem.enums.TransactionStatus;
import com.example.LibraryManagementSystem.exceptions.BookNotFoundException;
import com.example.LibraryManagementSystem.exceptions.CardNotFoundException;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.repository.CardRepository;
import com.example.LibraryManagementSystem.repository.TransactionRepository;
import com.example.LibraryManagementSystem.service.TransactionService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImp implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public TransactionResponseDto transactionOfIssueBook(TransactionRequestDto transactionRequestDto) throws BookNotFoundException, CardNotFoundException, MessagingException {
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setTransactionOperation(true);

        Book book;
        try{
            book = bookRepository.findById(transactionRequestDto.getBookId()).get();
            if(book.isIssued()){
                transaction.setTransactionStatus(TransactionStatus.FAILED);
                transactionRepository.save(transaction);
                throw  new BookNotFoundException("Book Not Available");
            }
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw  new BookNotFoundException("Invalid Book Id");
        }

        Card card;
        try{
            card=cardRepository.findById(transactionRequestDto.getCardId()).get();
            if(card.getStatus()== Status.BLOCKED || card.getStatus()==Status.EXPIRED){
                transaction.setTransactionStatus(TransactionStatus.FAILED);
                transactionRepository.save(transaction);
                throw new CardNotFoundException("Card Not Active");
            }
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardNotFoundException("Invalid Card Id");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        book.setIssued(true);
        book.setCard(card);
        book.getTransactionList().add(transaction);
        transaction.setBook(book);

        card.getTransactionList().add(transaction);
        List<Book> books=card.getBook();
        books.add(book);
        card.setBook(books);
        transaction.setCard(card);

        cardRepository.save(card); //As card is parent of book and transaction, so it will automatically save the other child database also.

        TransactionResponseDto transactionResponseDto =new TransactionResponseDto();
        transactionResponseDto.setTransactionNumber(transaction.getTransactionNumber());
        transactionResponseDto.setTransactionStatus(transaction.getTransactionStatus());
        transactionResponseDto.setTransactionDate(transaction.getTransactionDate());

        String text= "Congratulations!!  "+card.getStudent().getName()+"  The Book '"+book.getTitle()+"' is Successfully issued. Thank You!!!";


        SimpleMailMessage mailMessage = new SimpleMailMessage();

        // Setting up necessary details
        mailMessage.setFrom("abhisheklms0@gmail.com");
        mailMessage.setTo(card.getStudent().getEmail());
        mailMessage.setText(text);
        mailMessage.setSubject("Issued Book");

        // Sending the mail
        javaMailSender.send(mailMessage);

        return transactionResponseDto;
    }

    public TransactionResponseDto transactionOfReturnBook(TransactionRequestDto transactionRequestDto) throws BookNotFoundException, CardNotFoundException {
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setTransactionOperation(false);

        Book book;
        try{
            book = bookRepository.findById(transactionRequestDto.getBookId()).get();
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw  new BookNotFoundException("Invalid Book Id");
        }

        Card card;
        try{
            card=cardRepository.findById(transactionRequestDto.getCardId()).get();
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardNotFoundException("Invalid Card Id");
        }

//        if(book.isIssued()==true){
//            transaction.setTransactionStatus(TransactionStatus.FAILED);
//            transactionRepository.save(transaction);
//            throw  new BookNotFoundException("Book Not Available");
//        }

//        if(card.getStatus()== Status.BLOCKED || card.getStatus()==Status.EXPIRED){
//            transaction.setTransactionStatus(TransactionStatus.FAILED);
//            transactionRepository.save(transaction);
//            throw new CardNotFoundException("Card Not Active");
//        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        book.setIssued(false);
        book.getTransactionList().add(transaction);
        book.setCard(null);
        transaction.setBook(book);

        card.getTransactionList().add(transaction);
        List<Book> books=card.getBook();
        books.remove(book);
        card.setBook(books);
        transaction.setCard(card);



        cardRepository.save(card); //As card is parent of book and transaction, so it will automatically save the other child database also.

        TransactionResponseDto transactionResponseDto =new TransactionResponseDto();
        transactionResponseDto.setTransactionNumber(transaction.getTransactionNumber());
        transactionResponseDto.setTransactionStatus(transaction.getTransactionStatus());
        transactionResponseDto.setTransactionDate(transaction.getTransactionDate());


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String text= "Congratulations!!  "+card.getStudent().getName()+"  The Book '"+book.getTitle()+"' is Successfully Returned. Thank You!!!";
        // Setting up necessary details
        mailMessage.setFrom("abhisheklms0@gmail.com");
        mailMessage.setTo(card.getStudent().getEmail());
        mailMessage.setText(text);
        mailMessage.setSubject("Issued Book");

        // Sending the mail
        javaMailSender.send(mailMessage);
        return transactionResponseDto;
    }



}
