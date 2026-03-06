package com.aluracursos.literatura_challenge.model;

public enum Idiomas {

    INGLES ("en", "ingles"),
    ESPAÑOL("es", "español");

    private String idiomaGutendex;
    private String idiomaEspaniol;

    Idiomas(String idiomaGutendex, String idiomaEspaniol){
        this.idiomaGutendex = idiomaGutendex;
        this.idiomaEspaniol = idiomaEspaniol;
    }

    public static Idiomas fromString(String text){
        for(Idiomas idioma : Idiomas.values()){
            if(idioma.idiomaGutendex.equalsIgnoreCase(text)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: " + text);
    }

    public static Idiomas fromEspaniol(String text){
        for(Idiomas idioma : Idiomas.values()){
            if(idioma.idiomaEspaniol.equalsIgnoreCase(text)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: " + text);
    }
}
