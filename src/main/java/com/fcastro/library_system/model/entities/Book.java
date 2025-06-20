package com.fcastro.library_system.model.entities;

import com.fcastro.library_system.utils.StateBook;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "books")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false) 
    private String author;

    @Column(nullable = false) 
    private Long isbn;

    @Column
    private Integer yearOfPublication;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false) 
    private StateBook state;



}
