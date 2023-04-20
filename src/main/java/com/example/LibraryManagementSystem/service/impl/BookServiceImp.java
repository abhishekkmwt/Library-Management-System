package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.Entity.Author;
import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.dtos.requestDto.AddBookRequestDto;
import com.example.LibraryManagementSystem.dtos.requestDto.NoOfBooksByParticularAuthorRequestDto;
import com.example.LibraryManagementSystem.exceptions.AuthorNotFoundException;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;
    public String addBook(AddBookRequestDto addBookRequestDto) throws AuthorNotFoundException {

        try{
            Author author = authorRepository.findById(addBookRequestDto.getId()).get();

            Book book = new Book();
            book.setTitle(addBookRequestDto.getTitle());
            book.setGenre(addBookRequestDto.getGenre());
            book.setNoOfPages(addBookRequestDto.getNoOfPages());
            book.setPrice(addBookRequestDto.getPrice());
            book.setAuthor(author);

            author.getBook().add(book);
            authorRepository.save(author);
            return "Book Added Successfully";
        }
        catch(Exception e){
            throw new AuthorNotFoundException("Invalid Author id");
        }

    }

    public int noOfBooksByParticularAuthor(NoOfBooksByParticularAuthorRequestDto noOfBooksByParticularAuthorRequestDto) throws AuthorNotFoundException {
        try{
            Author author=authorRepository.findById(noOfBooksByParticularAuthorRequestDto.getId()).get();
            return author.getBook().size();
        }
        catch (Exception e){
            throw new AuthorNotFoundException("Invalid Author Id");
        }
    }
}
