package com.aluracursos.literatura_challenge;

import com.aluracursos.literatura_challenge.principal.Principal;
import com.aluracursos.literatura_challenge.repository.AutorRepository;
import com.aluracursos.literatura_challenge.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication                                           //
public class LiteraturaChallengeApplication implements CommandLineRunner {

    @Autowired
    private LibroRepository repositoryLibro;
    @Autowired
    private AutorRepository repositoryAutor;
	static void main(String[] args) {
		SpringApplication.run(LiteraturaChallengeApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(repositoryLibro, repositoryAutor);
        principal.muestraMenu();

    }
}
