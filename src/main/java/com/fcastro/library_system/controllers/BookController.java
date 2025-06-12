package com.fcastro.library_system.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fcastro.library_system.model.dto.BookDTO;
import com.fcastro.library_system.services.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/books")
@Tag(name = "Book Controller", description = "API para gestionar libros en la biblioteca")
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Obtener todos los libros", description = "Retorna una lista de todos los libros disponibles en la biblioteca")
    @ApiResponse(responseCode = "200", description = "Lista de libros encontrada exitosamente",
        content = @Content(mediaType = "application/json", 
        schema = @Schema(implementation = BookDTO.class)))
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getBooks();
    }

    @Operation(summary = "Obtener libro por ID", description = "Retorna un libro específico basado en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Libro encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @GetMapping("{id}")
    public BookDTO getBookById(
        @Parameter(description = "ID del libro a buscar", required = true) 
        @PathVariable Long id) {
        return bookService.getById(id);
    }
    
    @Operation(summary = "Crear nuevo libro", description = "Crea un nuevo libro en la biblioteca")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Libro creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos del libro inválidos")
    })
    @PostMapping
    public BookDTO createBook(
        @Parameter(description = "Datos del libro a crear", required = true) 
        @Valid @RequestBody BookDTO book) {
        return bookService.create(book);
    }
    
    @Operation(summary = "Actualizar libro", description = "Actualiza los datos de un libro existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Libro actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos del libro inválidos"),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @PutMapping("{id}")
    public BookDTO updateBook(
        @Parameter(description = "ID del libro a actualizar", required = true) 
        @PathVariable Long id,
        @Parameter(description = "Datos actualizados del libro", required = true) 
        @Valid @RequestBody BookDTO book) {
        return bookService.update(id, book);
    }

    @Operation(summary = "Eliminar libro", description = "Elimina un libro de la biblioteca")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Libro eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @DeleteMapping("{id}")
    public void deleteBook(
        @Parameter(description = "ID del libro a eliminar", required = true) 
        @PathVariable Long id){
        bookService.delete(id);
    }

    @Operation(summary = "Buscar libros", description = "Busca libros por título o autor")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetro de búsqueda inválido")
    })
    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBooks(
        @Parameter(description = "Texto a buscar en títulos o autores", required = true) 
        @Valid @RequestParam("q") String searchText) {
        return ResponseEntity.ok(bookService.searchBook(searchText));
    }

    @Operation(summary = "Prestar libro", description = "Marca un libro como prestado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Libro prestado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado"),
        @ApiResponse(responseCode = "400", description = "El libro ya está prestado")
    })
    @PostMapping("/{id}/lend")
    public ResponseEntity<BookDTO> lendBook(
        @Parameter(description = "ID del libro a prestar", required = true) 
        @PathVariable Long id) {
        BookDTO borrowedBook = bookService.lenBook(id);
        return ResponseEntity.ok(borrowedBook);
    }
}
