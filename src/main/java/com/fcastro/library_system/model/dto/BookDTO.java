package com.fcastro.library_system.model.dto;

import com.fcastro.library_system.utils.StateBook;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para la gestión de libros en la biblioteca")
public class BookDTO {
    
    @Schema(description = "Identificador único del libro", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Título del libro", example = "El Quijote", required = true)
    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    @Schema(description = "Nombre del autor del libro", example = "Miguel de Cervantes", required = true)
    @NotBlank(message = "El autor no puede estar vacío")
    private String author;

    @Schema(description = "Número ISBN del libro (13 dígitos)", example = "9788497593538", required = true)
    @NotNull(message = "El ISBN no puede ser nulo")
    @Digits(integer = 13, fraction = 0, message = "El ISBN debe tener máximo 13 dígitos") 
    private Long isbn;

    @Schema(description = "Año de publicación del libro", example = "1605", required = true, 
            minimum = "1000", maximum = "9999")
    @NotNull(message = "El año de publicación no puede ser nulo")
    @Min(value = 1000, message = "El año debe ser mayor a 1000")
    @Max(value = 9999, message = "El año debe tener 4 dígitos")
    private Integer yearOfPublication;

    @Schema(description = "Género literario del libro", example = "Novela", required = true)
    @NotBlank(message = "El género no puede estar vacío")
    private String gender;

    @Schema(description = "Estado actual del libro en la biblioteca", 
            example = "AVAILABLE", 
            required = true,
            allowableValues = {"AVAILABLE", "BORROWED"})
    @NotNull(message = "El estado no puede ser nulo")
    private StateBook state;
}
