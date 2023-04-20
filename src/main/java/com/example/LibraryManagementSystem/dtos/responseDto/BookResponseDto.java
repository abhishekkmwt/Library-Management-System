package com.example.LibraryManagementSystem.dtos.responseDto;

import com.example.LibraryManagementSystem.enums.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookResponseDto {

    private int id;

    private String title;

    private Genre genre;

    private int noOfPages;

    private int price;
}
