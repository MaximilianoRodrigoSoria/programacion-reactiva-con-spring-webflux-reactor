package com.lab.reactor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
@Slf4j
public class SpringBootReactorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootReactorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        Flux<String> nombres = Flux.just("Maximiliano", "", "Bishop", "Eluney", "Shinna")
                .doOnNext(elememto ->
                        {
                            if(elememto.isEmpty()){
                                throw new RuntimeException("No se puede cargar el elemento ya esta vacio");
                            }
                            System.out.println(elememto);
                        }
                        );
        nombres.subscribe(e -> log.info(e), error -> log.error(error.getMessage()));
    }
}
