package com.aluracursos.literatura_challenge.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);

}
