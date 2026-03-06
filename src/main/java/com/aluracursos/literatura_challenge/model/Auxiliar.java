package com.aluracursos.literatura_challenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Auxiliar(
        @JsonAlias("results") List<DatosLibro> resultados) {
}
