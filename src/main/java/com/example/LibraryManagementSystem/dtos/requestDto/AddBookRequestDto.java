package com.example.LibraryManagementSystem.dtos.requestDto;

import com.example.LibraryManagementSystem.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddBookRequestDto {

    private int id;

    private String title;

    private Genre genre;

    private int noOfPages;

    private int price;
}
