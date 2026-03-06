package com.aluracursos.literatura_challenge.principal;

import com.aluracursos.literatura_challenge.model.*;
import com.aluracursos.literatura_challenge.repository.AutorRepository;
import com.aluracursos.literatura_challenge.repository.LibroRepository;
import com.aluracursos.literatura_challenge.service.ConsumoAPI;
import com.aluracursos.literatura_challenge.service.ConvierteDatos;
import jakarta.transaction.Transactional;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Principal {
    private String json;
    private DatosLibro datosLibro;
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<Libro> listaDatosLibros = new ArrayList();
    private List<Autor> listaDatosAutores = new ArrayList();
    private Autor autor;
    private LibroRepository repositorioLibro;
    private AutorRepository repositorioAutor;

    public Principal(){}

    public Principal(LibroRepository repositoryLibro, AutorRepository repositoryAutor){
        this.repositorioLibro = repositoryLibro;
        this.repositorioAutor = repositoryAutor;
    }

    public void muestraMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    Elige una opción válida
                    
                    1 - Buscar libro en la API y guardarlo 
                    2 - Mostrar libros registrados
                    3 - Mostrar autores registrados
                    4 - Mostrar autores vivos en un determinado año
                    5 - Mostrar libros por idioma
                    
                    0 - Salir
                    
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWebPorTitulo();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresVivosPorAnio();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private Auxiliar getDatosLibro(){
        System.out.println("Ingresa el nombre del libro que deseas buscar");
        String busqueda = teclado.nextLine();
        String busquedaEncoded = URLEncoder.encode(busqueda, StandardCharsets.UTF_8);
        json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + busquedaEncoded);

        Auxiliar datos = conversor.obtenerDatos(json,Auxiliar.class);

        return datos;
    }
    @Transactional
    private void buscarLibroWebPorTitulo(){
        datosLibro = getDatosLibro().resultados().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hubo resultados"));
        DatosAutor datosAutor = datosLibro.autor()
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("El libro no tiene autor"));
        String tituloDelLibroBuscado = datosLibro.titulo();
        String nombreDelAutorDelTituloBuscado = datosAutor.nombre();

        Optional<Libro> libroExistenteBD = repositorioLibro.findByTituloContainsIgnoreCase(tituloDelLibroBuscado);
        Optional<Autor> autorExistenteBD = repositorioAutor.findByNombreIgnoreCase(nombreDelAutorDelTituloBuscado);


        if(libroExistenteBD.isPresent()){
            System.out.println("\nLos datos ya están registrados\n");
        } else {
            Libro libroBuscadoWeb = new Libro(datosLibro);
            if(autorExistenteBD.isPresent()){
                autor = autorExistenteBD.get();
                autor.addLibro(libroBuscadoWeb);
                libroBuscadoWeb.setAutor(autor);
                repositorioLibro.save(libroBuscadoWeb);
                System.out.println("\nEl autor ya se encuentra registrado. Libro registrado exitosamente\n");
            } else {
                Autor autorLibroWeb = new Autor(datosAutor);
                libroBuscadoWeb.setAutor(autorLibroWeb);
                repositorioAutor.save(autorLibroWeb);
                repositorioLibro.save(libroBuscadoWeb);
                System.out.println("\nLibro y autor registrados exitosamente\n");
            }
        }
        separador();
    }

    @Transactional
    private void mostrarLibrosBuscados(){
        listaDatosLibros = repositorioLibro.findAllConAutor();
        listaDatosLibros.stream().forEach(System.out::println);
        separador();
    }

    private void mostrarAutoresRegistrados(){
        listaDatosAutores = repositorioAutor.findAll();
        listaDatosAutores.stream().forEach(System.out::println);
        separador();
    }

    private void mostrarAutoresVivosPorAnio(){
        System.out.println("\nIngrese el año del que desea ver los autores que estuvieron vivos");
        Integer anio = teclado.nextInt();
        teclado.nextLine();
        listaDatosAutores = repositorioAutor.findAutoresVivosPorAnio(anio);

        if(!listaDatosAutores.isEmpty()){
            listaDatosAutores.stream().forEach(System.out::println);
        } else {
            System.out.println("\nNo hay autores registrados vivos en ese año");
        }
        separador();
    }

    private void mostrarLibrosPorIdioma(){
        System.out.println("\nIngrese el idioma de los libros que quiere listar");
        var busqueda = teclado.nextLine();
        var lengua = Idiomas.fromEspaniol(busqueda);
        List<Libro> listaLibrosPorIdioma = repositorioLibro.findByIdiomaConAutor(lengua);
        listaLibrosPorIdioma.stream().forEach(System.out::println);
        separador();
    }

    private void separador(){
        System.out.println("\n-------------------------------------------------------\n");
    }
}
