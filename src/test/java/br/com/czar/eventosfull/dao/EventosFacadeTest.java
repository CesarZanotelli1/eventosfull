package br.com.czar.eventosfull.dao;

import br.com.czar.eventosfull.models.Eventos;
import br.com.czar.eventosfull.models.EventosDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;


import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EventosFacadeTest {

    @Mock
    private EventosRepository repository;

    @Autowired
    @InjectMocks
    private EventosFacade facade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Deve deixar criar um evento no DB")
    void criar() {
        EventosDTO dto = new EventosDTO();
        dto.setId(1);
        dto.setDescricao("teste");
        dto.setData(new Date());

        EventosDTO test = new EventosDTO();

        test = facade.criar(dto);

        verify(repository, times(1)).save(any());
        assertThat(test.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("Deve deixar atualizar um evento no DB")
    void atualizar() {


        EventosDTO eventosDTO = new EventosDTO();
        eventosDTO.setId(1);
        eventosDTO.setDescricao("teste1");
        eventosDTO.setData(new Date());

        Eventos data = new Eventos();
        data.setId(1);
        data.setDescricao("teste");
        data.setData(new Date());


        when(repository.getOne(1)).thenReturn(data);

        facade.atualizar(eventosDTO, 1);


        verify(repository, times(1)).save(any());
        assertThat(eventosDTO.getDescricao()).isEqualTo(data.getDescricao());
    }

    @Test
    @DisplayName("Deve retornar o evento correto, com a mesma descrição")
    void buscarPorId() {
        Eventos data = new Eventos();
        data.setId(1);
        data.setDescricao("opa");
        data.setData(new Date());

        when(repository.getOne(1)).thenReturn(data);

        assertThat(facade.buscarPorId(1).getDescricao())
                .isEqualTo(data.getDescricao());

        assertThat(facade.buscarPorId(1).getData())
        .isEqualTo(data.getData());

        assertThat(facade.buscarPorId(1).getId())
        .isEqualTo(1);
    }

    @Test
    @DisplayName("Deve retonar uma lista com 2 eventos")
    void buscarTodos() {
        Eventos data = new Eventos();
        data.setId(1);
        data.setDescricao("teste");
        data.setData(new Date());

        Eventos data2 = new Eventos();
        data.setId(2);
        data.setDescricao("teste");
        data.setData(new Date());

        List<Eventos> eventos = new ArrayList<>();
        eventos.add(data);
        eventos.add(data2);

       when(repository.findAll()).thenReturn(eventos);

        List<EventosDTO> dtos = facade.buscarTodos();

        Assertions.assertEquals(2, dtos.size());

    }

    @Test
    @DisplayName("Deve retornar uma menssagem de sucesso!")
    void remover() {
        String exc = "Removido com sucesso";

        String test = facade.remover(1);
        Assertions.assertEquals(exc, test);

    }


}