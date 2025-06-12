# Sistema de Gestión de Biblioteca 📚

## Descripción
Sistema de gestión de biblioteca desarrollado con Spring Boot que permite administrar el inventario de libros, realizar préstamos y gestionar el estado de los libros en la biblioteca.

## Tecnologías Utilizadas
- Java 21
- Spring Boot 3.5.0
- Spring Data JPA
- MySQL
- Lombok
- ModelMapper
- SpringDoc OpenAPI (Swagger)
- Maven

## Requisitos Previos
- JDK 21 o superior
- Maven 3.6 o superior
- MySQL 8.0 o superior

## Configuración del Proyecto

### 1. Clonar el Repositorio
```bash
git clone https://github.com/tu-usuario/library-system.git
cd library-system
```

### 2. Configurar Base de Datos
1. Crear una base de datos MySQL:
```sql
CREATE DATABASE library_system;
```

2. Configurar las credenciales en `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_system
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

### 3. Compilar y Ejecutar
```bash
mvn clean install
mvn spring-boot:run
```

## Documentación de la API
La documentación completa de la API está disponible a través de Swagger UI:
- URL: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Endpoints Disponibles

### Gestión de Libros

#### Obtener Todos los Libros
- **GET** `/api/books`
- Retorna la lista completa de libros disponibles

#### Obtener Libro por ID
- **GET** `/api/books/{id}`
- Retorna los detalles de un libro específico

#### Crear Nuevo Libro
- **POST** `/api/books`
- Crea un nuevo libro en el sistema
- Body:
```json
{
    "title": "El Quijote",
    "author": "Miguel de Cervantes",
    "isbn": 9788497593538,
    "yearOfPublication": 1605,
    "gender": "Novela",
    "state": "AVAILABLE"
}
```

#### Actualizar Libro
- **PUT** `/api/books/{id}`
- Actualiza la información de un libro existente
- Body: Mismo formato que crear libro

#### Eliminar Libro
- **DELETE** `/api/books/{id}`
- Elimina un libro del sistema

#### Buscar Libros
- **GET** `/api/books/search?q=texto`
- Busca libros por título o autor

#### Prestar Libro
- **POST** `/api/books/{id}/lend`
- Marca un libro como prestado

## Validaciones

### Libro (BookDTO)
- **Título**: No puede estar vacío
- **Autor**: No puede estar vacío
- **ISBN**: 
  - No puede ser nulo
  - Debe tener exactamente 13 dígitos
- **Año de Publicación**: 
  - No puede ser nulo
  - Debe ser un año válido (entre 1000 y 9999)
- **Género**: No puede estar vacío
- **Estado**: 
  - No puede ser nulo
  - Valores permitidos: AVAILABLE, BORROWED

## Estructura del Proyecto
```
src/main/java/com/fcastro/library_system/
├── controllers/         # Controladores REST
├── model/
│   ├── dto/            # Objetos de transferencia de datos
│   └── entities/       # Entidades JPA
├── repository/         # Repositorios de datos
├── services/          # Lógica de negocio
│   └── impl/          # Implementaciones de servicios
└── utils/             # Utilidades y enums
```

## Contribución
1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## Licencia
Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## Contacto
Tu Nombre - [@tu_twitter](https://twitter.com/tu_twitter) - email@ejemplo.com

Link del Proyecto: [https://github.com/tu-usuario/library-system](https://github.com/tu-usuario/library-system) 