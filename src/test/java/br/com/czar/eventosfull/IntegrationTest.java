package br.com.czar.eventosfull;


import br.com.czar.eventosfull.containers.PostgresTestContainer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = EventosfullApplication.class)
@ExtendWith({
        PostgresTestContainer.class
})
@AutoConfigureWebMvc
@AutoConfigureMockMvc
@ImportAutoConfiguration
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public @interface IntegrationTest {
}
