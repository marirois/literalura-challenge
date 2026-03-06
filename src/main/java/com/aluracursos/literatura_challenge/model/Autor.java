package com.aluracursos.literatura_challenge.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
        // ..... ATRITUBOS .....
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    @Column(nullable = true)
    private Integer anioNacimiento;
    @Column(nullable = true)
    private Integer anioFallecimiento;
    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)    private List<Libro> libros = new ArrayList<>();

        // ..... CONSTRUCTORES .....
    public Autor(){}

    public Autor(DatosAutor a){
        this.nombre = a.nombre();
        this.nombre = a.nombre();
        this.anioNacimiento = Optional.ofNullable(a.anioNacimiento()).orElse(0);
        this.anioFallecimiento = Optional.ofNullable(a.anioFallecimiento()).orElse(0);
    }

    // ----- GETTERS -----
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

        // ----- SETTERS -----
    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public void setAnioFallecimiento(int anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public void setLibros(List<Libro> libros) {
        libros.forEach(l -> l.setAutor(this));
        this.libros = libros;
    }

    public void addLibro(Libro libro){
        this.libros.add(libro);
        libro.setAutor(this);
    }

        // ---- TOSTRING -----

    @Override
    public String toString() {
        return "\nNombre: " + this.nombre +
                "\nAño de nacimiento: " + this.anioNacimiento +
                "\nAño de fallecimiento: " + this.anioFallecimiento +
                "\nLibros: " + libros.stream().map(Libro::getTitulo)
                .collect(Collectors.joining(" // ")) + "\n";
    }
}
