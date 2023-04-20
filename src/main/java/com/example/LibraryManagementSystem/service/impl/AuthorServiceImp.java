package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.Entity.Author;
import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.dtos.requestDto.AddAuthorRequestDto;
import com.example.LibraryManagementSystem.dtos.requestDto.FindAllAuthorsWithParticularAgeRequestDto;
import com.example.LibraryManagementSystem.dtos.requestDto.FindAuthorByEmailRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.BookResponseDto;
import com.example.LibraryManagementSystem.dtos.responseDto.FindAuthorResponseDto;
import com.example.LibraryManagementSystem.dtos.responseDto.FindParticularAgeAuthorResponseDto;
import com.example.LibraryManagementSystem.dtos.responseDto.FindStudentResponseDto;
import com.example.LibraryManagementSystem.exceptions.AuthorNotFoundException;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AuthorServiceImp implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(AddAuthorRequestDto addAuthorRequestDto){
        Author author=new Author();
        author.setAge(addAuthorRequestDto.getAge());
        author.setEmail(addAuthorRequestDto.getEmail());
        author.setName(addAuthorRequestDto.getName());
        authorRepository.save(author);
        return "Author Added Successfully";
    }

    public String deleteAuthorById(int id){
        authorRepository.deleteById(id);
        return "Author Deleted Successfully";
    }

    public FindAuthorResponseDto findAuthorByEmail(FindAuthorByEmailRequestDto findAuthorByEmailRequestDto) throws AuthorNotFoundException {
             try{
                 Author author = authorRepository.findByEmail(findAuthorByEmailRequestDto.getEmail());
                 FindAuthorResponseDto findAuthorResponseDto =new FindAuthorResponseDto();
                 findAuthorResponseDto.setId(author.getId());
                 findAuthorResponseDto.setName(author.getName());
                 findAuthorResponseDto.setEmail(author.getEmail());
                 findAuthorResponseDto.setAge(author.getAge());

                 List<BookResponseDto> list=new ArrayList<>();
                 for(Book book : author.getBook()){
                     BookResponseDto bookResponseDto = new BookResponseDto();
                     bookResponseDto.setGenre(book.getGenre());
                     bookResponseDto.setId(book.getId());
                     bookResponseDto.setTitle(book.getTitle());
                     bookResponseDto.setPrice(book.getPrice());
                     bookResponseDto.setNoOfPages(book.getNoOfPages());

                     list.add(bookResponseDto);
                 }

                 findAuthorResponseDto.setBookResponseDto(list);
                 return findAuthorResponseDto;
             }
             catch (Exception e){
                 throw new AuthorNotFoundException("Email Does Not Exists.");
             }
    }
    public List<FindParticularAgeAuthorResponseDto> allAuthorsOfParticularAge(FindAllAuthorsWithParticularAgeRequestDto findAllAuthorsWithParticularAgeRequestDto){
        List<Author> authors= authorRepository.findAll();
        List<FindParticularAgeAuthorResponseDto> list =new ArrayList<>();
        for(Author a : authors){
            if(a.getAge() == findAllAuthorsWithParticularAgeRequestDto.getAge()){
                FindParticularAgeAuthorResponseDto findParticularAgeAuthorResponseDto=new FindParticularAgeAuthorResponseDto();
                findParticularAgeAuthorResponseDto.setAge(a.getAge());
                findParticularAgeAuthorResponseDto.setId(a.getId());
                findParticularAgeAuthorResponseDto.setEmail(a.getEmail());
                findParticularAgeAuthorResponseDto.setName(a.getName());

                list.add(findParticularAgeAuthorResponseDto);
            }
        }
        return list;
    }
}
