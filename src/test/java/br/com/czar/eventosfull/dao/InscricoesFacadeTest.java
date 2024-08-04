package br.com.czar.eventosfull.dao;

import br.com.czar.eventosfull.models.Inscricoes;
import br.com.czar.eventosfull.models.InscricoesDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
class InscricoesFacadeTest {
    @Mock
    private InscricoesRepository repository;

    @Autowired
    @InjectMocks
    private InscricoesFacade facade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void criar() {

        InscricoesDTO inscricoesDTO = new InscricoesDTO();
        inscricoesDTO.setId(1);
        inscricoesDTO.setIdUser(1);
        inscricoesDTO.setIdEvent(1);

        InscricoesDTO test = new InscricoesDTO();
        test = facade.criar(inscricoesDTO);

        verify(repository, times(1)).save(any());
        assertThat(test.getStatus()).isEqualTo(0);

    }

    @Test
    void atualizar() {

        InscricoesDTO ins = new InscricoesDTO();
        ins.setId(1);
        ins.setIdUser(1);
        ins.setIdEvent(1);


        Inscricoes data = new Inscricoes();
        data.setId(1);
        data.setIdUser(1);
        data.setIdEvent(1);
        data.setStatus(1);

        when(repository.findByUserandEvent(1, 1)).thenReturn(data);


        facade.atualizar(ins, 2);


        verify(repository, times(1)).save(any());
        assertThat(ins.getStatus()).isEqualTo(data.getStatus());

    }

    @Test
    void buscarPorId() {

        Inscricoes data = new Inscricoes();
        data.setId(1);
        data.setIdUser(1);
        data.setIdEvent(1);
        data.setStatus(0);

        when(repository.getOne(1)).thenReturn(data);

        InscricoesDTO test = facade.buscarPorId(1);

        verify(repository, times(1)).getOne(1);
        assertThat(test.getId()).isEqualTo(data.getId());
        assertThat(test.getIdUser()).isEqualTo(data.getIdUser());
        assertThat(test.getIdEvent()).isEqualTo(data.getIdEvent());
        assertThat(test.getStatus()).isEqualTo(0);
    }

    @Test
    void buscarTodos() {

        Inscricoes data = new Inscricoes();
        data.setId(1);
        data.setIdUser(1);
        data.setIdEvent(1);
        data.setStatus(0);

        Inscricoes data2 = new Inscricoes();
        data.setId(2);
        data.setIdUser(2);
        data.setIdEvent(1);
        data.setStatus(1);

        List<Inscricoes> list = new ArrayList<Inscricoes>();
        list.add(data);
        list.add(data2);

        when(repository.findAll()).thenReturn(list);

        List<InscricoesDTO> test = facade.buscarTodos();
        verify(repository, times(1)).findAll();
        assertThat(test.size()).isEqualTo(2);

    }

    @Test
    void remover() {
        String test = "Removido com sucesso";

        String result = facade.remover(1);
        assertThat(result).isEqualTo(test);
    }
}