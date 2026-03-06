package com.aluracursos.literatura_challenge.model;

import jakarta.persistence.*;


@Entity
@Table(name = "libros")
public class Libro {
        // ..... ATRITUBOS .....
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;

    @Enumerated(EnumType.STRING)
    private Idiomas idioma;
    private Double numeroDescargas;

    @ManyToOne(fetch = FetchType.LAZY)
    private Autor autor;

        // ..... Constructores .....
    public Libro(){}

    public Libro(DatosLibro l){
        this.titulo = l.titulo();
        String lang = (l.idiomas() != null && !l.idiomas().isEmpty()) ? l.idiomas().get(0) : null;
        this.idioma = Idiomas.fromString(lang);
        this.numeroDescargas = l.numeroDeDescargas();
    }


        // ----- GETTERS -----
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Idiomas getIdiomas() {
        return idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    // ----- SETTERS -----

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(Autor a) {
        this.autor = a;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    // ---- TOSTRING -----

    @Override
    public String toString() {
        return "Titulo: " + this.titulo +
                " - Idioma: " + this.idioma +
                " - Autor: " + this.autor.getNombre();
    }
}
