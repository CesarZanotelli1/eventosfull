package br.com.czar.eventosfull;


import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EntityScan("br.com.czar.eventosfull")
@EnableJpaRepositories(basePackages = "br.com.czar.eventosfull")
public class EventosfullApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventosfullApplication.class, args);
    }

}
