/*
package com.aluracursos.literatura_challenge.principal;

import com.aluracursos.literatura_challenge.model.Autor;
import com.aluracursos.literatura_challenge.model.Auxiliar;
import com.aluracursos.literatura_challenge.model.DatosLibro;
import com.aluracursos.literatura_challenge.model.Libro;
import com.aluracursos.literatura_challenge.repository.LibroRepository;
import com.aluracursos.literatura_challenge.service.ConsumoAPI;
import com.aluracursos.literatura_challenge.service.ConvierteDatos;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PrincipalNuevo {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;

    public PrincipalNuevo(LibroRepository repository){
        this.repositorio = repository;
    }

    public void muestraMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    Elige una opción válida
                    
                    1 - Buscar libro por título 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    
                    0 - Salir
                    
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    //mostrarLibrosRegistrados();
                    break;
                case 3:
                    //mostrarAutoresRegistrados();
                    break;
                case 4:
                    //mostrarAutoresVivosPorAnio();
                    break;
                case 5:
                    //mostrarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
--------------------------------------------------
    private void buscarLibroWebPorTitulo(){
        datosLibro = getDatosLibro().resultados().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hubo resultados"));
        DatosAutor datosAutor = datosLibro.autor()
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("El libro no tiene autor"));

        Libro libroBuscado = new Libro(datosLibro);
        Autor autorLibro = new Autor(datosAutor);


        libroBuscado.setAutor(autorLibro);
        autorLibro.setLibros(List.of(libroBuscado));

        repositorio.save(libroBuscado);
    }
------------------------------------------------------
    */
/*private DatosLibro getDatosLibro(){

    }*//*



}
*/
