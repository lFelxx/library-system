package com.fcastro.library_system.services;

import java.util.List;

import com.fcastro.library_system.model.dto.BookDTO;

public interface BookService {
    List<BookDTO> getBooks();

    BookDTO getById(Long id);

    BookDTO create(BookDTO book);

    BookDTO update(Long id, BookDTO book);

    void delete(Long id);

    List<BookDTO> searchBook(String searchText);

    BookDTO lenBook(Long id);
}
