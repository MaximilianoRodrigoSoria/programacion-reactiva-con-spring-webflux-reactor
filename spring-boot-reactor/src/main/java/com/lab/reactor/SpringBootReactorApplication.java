package com.lab.reactor;

import com.lab.reactor.model.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.util.Objects;

@SpringBootApplication
@Slf4j
public class SpringBootReactorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootReactorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        Flux<Usuario> nombres = Flux.just("Maximiliano Soria", "Lionel Contrera", "Bishop Soria", "Eluney Contrera", "Shinna Contrera")
                .map(nombre -> new Usuario(nombre.split(" ")[0], nombre.split(" ")[1]))
                .doOnNext(usuario ->
                        {
                            if(Objects.isNull(usuario)){
                                throw new RuntimeException("No se puede cargar el usuario");
                            }
                            System.out.println(usuario.getNombre());
                        }
                        )
                .map(usuario -> {
                    usuario.setNombre(usuario.getNombre().toUpperCase());
                    return usuario;
                })
               .filter(usuario -> !usuario.getApellido().toUpperCase().equals("CONTRERA"));

        nombres.subscribe(e -> log.info(e.toString()), error -> log.error(error.getMessage()));
    }
}
