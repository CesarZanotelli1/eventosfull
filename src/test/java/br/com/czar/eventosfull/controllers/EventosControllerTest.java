package br.com.czar.eventosfull.controllers;

import br.com.czar.eventosfull.IntegrationTest;
import br.com.czar.eventosfull.dao.EventosRepository;
import br.com.czar.eventosfull.models.Eventos;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Date;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@IntegrationTest
class EventosControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    EventosRepository eventosRepository;


    @Test
    void addEvento() {

    }

    @Test
    void updateEvento() {
    }

    @Test
    void getAllEventos() throws Exception {

        Eventos eventos = new Eventos();
        eventos.setId(1);
        eventos.setDescricao("teste");
        eventos.setData(new Date());

        Eventos eventos2 = new Eventos();
        eventos2.setId(2);
        eventos2.setDescricao("teste2");
        eventos2.setData(new Date());

        Eventos eventos3 = new Eventos();
        eventos3.setId(3);
        eventos3.setDescricao("teste3");
        eventos3.setData(new Date());

        eventosRepository.save(eventos);
        eventosRepository.save(eventos2);
        eventosRepository.save(eventos3);


        mvc.perform(
                get("/eventosfull/eventos").header("user", "admin").header("pass", "admin")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));

    }

    @Test
    void getEventoById() {
    }

    @Test
    void deleteEventoById() {
    }
}