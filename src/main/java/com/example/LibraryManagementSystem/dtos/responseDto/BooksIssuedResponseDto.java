package com.example.LibraryManagementSystem.dtos.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BooksIssuedResponseDto {

    private int bookId;

    private String title;
}
