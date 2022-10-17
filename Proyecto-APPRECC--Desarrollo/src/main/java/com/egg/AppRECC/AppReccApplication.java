package com.egg.AppRECC;

import com.egg.AppRECC.entidades.Usuario;
import com.egg.AppRECC.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
	
public class AppReccApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(AppReccApplication.class, args);
    }

    @Autowired
    private UsuarioRepositorio repositorio;

    @Override
    public void run(String... args) throws Exception {

	/* Usuario usuario1 = new Usuario("Diego","Reartes","Reartes17dieg@gmail.com");
	 repositorio.save(usuario1);

	 Usuario usuario2 = new Usuario("Celeste","Gonzalez","celeste@gmail.com");
	 repositorio.save(usuario2);*/
    }

}