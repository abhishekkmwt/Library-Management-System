package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Entity.Card;
import com.example.LibraryManagementSystem.dtos.requestDto.BooksCurrentlyIssuedByCardRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.BooksCurrentlyIssuedByCardResponseDto;
import com.example.LibraryManagementSystem.dtos.responseDto.BooksIssuedResponseDto;
import com.example.LibraryManagementSystem.exceptions.CardNotFoundException;
import com.example.LibraryManagementSystem.repository.CardRepository;
import com.example.LibraryManagementSystem.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImp implements CardService {
    @Autowired
    CardRepository cardRepository;

    @Override
    public BooksCurrentlyIssuedByCardResponseDto listOfBooksIssued(BooksCurrentlyIssuedByCardRequestDto booksCurrentlyIssuedByCardRequestDto) throws CardNotFoundException {

        try{
            Card card=cardRepository.findById(booksCurrentlyIssuedByCardRequestDto.getCardId()).get();
            BooksCurrentlyIssuedByCardResponseDto booksCurrentlyIssuedByCardResponseDto=new BooksCurrentlyIssuedByCardResponseDto();
            booksCurrentlyIssuedByCardResponseDto.setCardId(card.getId());

            List<BooksIssuedResponseDto> list=new ArrayList<>();
            for(Book b: card.getBook()){
                BooksIssuedResponseDto booksIssuedResponseDto=new BooksIssuedResponseDto();
                booksIssuedResponseDto.setBookId(b.getId());
                booksIssuedResponseDto.setTitle(b.getTitle());

                list.add(booksIssuedResponseDto);
            }
            booksCurrentlyIssuedByCardResponseDto.setBooksIssuedResponseDto(list);
            return booksCurrentlyIssuedByCardResponseDto;

        }
        catch(Exception e){
            throw new CardNotFoundException("Invalid card Id");
        }
    }

    @Override
    public int noOfBookIssuedCurrentlyToParticularStudent(int id) throws CardNotFoundException {
        try{
            Card card= cardRepository.findById(id).get();
            List<Book> books =card.getBook();
            int ans =books.size();
            return ans;

        }catch (Exception e){
            throw new CardNotFoundException("Invalid Card Id");
        }
    }
}
