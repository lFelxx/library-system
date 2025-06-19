package com.fcastro.library_system.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fcastro.library_system.model.dto.BookDTO;
import com.fcastro.library_system.model.entities.Book;
import com.fcastro.library_system.repository.BookRepository;
import com.fcastro.library_system.services.BookService;
import com.fcastro.library_system.utils.StateBook;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    
    @Override
    public List<BookDTO> getBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream()
            .map(book -> modelMapper.map(book, BookDTO.class))
            .toList();
    }

    @Override
    public BookDTO getById(Long id) {
        return bookRepository.findById(id)
        .map(book -> modelMapper.map(book, BookDTO.class))
        .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "Libro con ID " + id + " no encontrado"));
    }

    @Override
    public BookDTO create(BookDTO book) {
        Book entity = modelMapper.map(book, Book.class);
        Book save = bookRepository.save(entity);

        return modelMapper.map(save, BookDTO.class);
    }

    @Override
    public BookDTO update(Long id, BookDTO book) {
        Book existingBook = bookRepository.findById(id)
            .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "Libro con ID " + id + " no encontrado"));

            modelMapper.map(book, existingBook);

            Book updateBook = bookRepository.save(existingBook);

            return modelMapper.map(updateBook, BookDTO.class);
    }

    @Override
    public void delete(Long id) {
         bookRepository.findById(id)
        .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "Libro con ID " + id + " no encontrado"));
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDTO> searchBook(String searchText) {
        List<Book> results = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(searchText, searchText);

        return results.stream()
            .map(book -> modelMapper.map(book, BookDTO.class))
            .toList();
    }

    @Override
    public BookDTO lenBook(Long id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "Libro con ID " + id + " no encontrado"));

        
        if (book.getState() != StateBook.AVAILABLE) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El libro no esta dispoible para el prestamo");
        }

        book.setState(StateBook.BORROWED);

        Book bookUpdate = bookRepository.save(book);

        return modelMapper.map(bookUpdate, BookDTO.class);
    }
}
