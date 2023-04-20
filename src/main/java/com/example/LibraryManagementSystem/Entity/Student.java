package com.example.LibraryManagementSystem.Entity;

import com.example.LibraryManagementSystem.enums.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Department department;

    private int age;

    private String mobNo;

    private String email;

    @OneToOne(mappedBy = "student", cascade =CascadeType.ALL)
    Card card;

}
