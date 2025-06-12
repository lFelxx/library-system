package com.fcastro.library_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fcastro.library_system.model.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);
    
}
