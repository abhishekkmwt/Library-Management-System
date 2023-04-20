package com.example.LibraryManagementSystem.dtos.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BooksCurrentlyIssuedByCardResponseDto {

    private int cardId;

    List<BooksIssuedResponseDto> booksIssuedResponseDto;

}
