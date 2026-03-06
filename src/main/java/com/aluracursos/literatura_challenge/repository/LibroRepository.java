package com.aluracursos.literatura_challenge.repository;

import com.aluracursos.literatura_challenge.model.Autor;
import com.aluracursos.literatura_challenge.model.Idiomas;
import com.aluracursos.literatura_challenge.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {


    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);

    @Query("SELECT l FROM Libro l JOIN FETCH l.autor")
    List<Libro> findAllConAutor();

    @Query("""
    SELECT l
    FROM Libro l
    JOIN FETCH l.autor
    WHERE l.idioma = :idioma
    """)
    List<Libro> findByIdiomaConAutor(@Param("idioma") Idiomas idioma);
}
