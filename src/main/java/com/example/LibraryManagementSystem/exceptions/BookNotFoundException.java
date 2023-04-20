package com.example.LibraryManagementSystem.exceptions;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String message){
        super(message);
    }
}
