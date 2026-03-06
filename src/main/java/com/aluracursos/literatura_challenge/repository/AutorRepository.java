package com.aluracursos.literatura_challenge.repository;

import com.aluracursos.literatura_challenge.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNombreIgnoreCase(String nombreAutor);

    @Query("""
            SELECT a FROM Autor a 
            WHERE a.anioNacimiento <= :anio AND 
            (a.anioFallecimiento >= :anio OR a.anioFallecimiento IS NULL)
            """)
    List<Autor> findAutoresVivosPorAnio(Integer anio);
}

