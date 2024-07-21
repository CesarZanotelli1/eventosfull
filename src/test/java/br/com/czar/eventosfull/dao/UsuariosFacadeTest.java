package br.com.czar.eventosfull.dao;


import br.com.czar.eventosfull.models.Usuarios;
import br.com.czar.eventosfull.models.UsuariosDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuariosFacadeTest {

    @Mock
    private UsuariosRepository repository;

    @Autowired
    @InjectMocks
    private UsuariosFacade facade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void criar() {

        UsuariosDTO usuariosDTO = new UsuariosDTO();
        usuariosDTO.setId(1);
        usuariosDTO.setNome("Teste");
        usuariosDTO.setEmail("email@email.com");
        usuariosDTO.setSenha("senha");


        UsuariosDTO test = new UsuariosDTO();
        test = facade.criar(usuariosDTO);

        verify(repository, times(1)).save(any());
        assertThat(test.getEmail()).isEqualTo(usuariosDTO.getEmail());

    }

    @Test
    void atualizar() {
        UsuariosDTO usuariosDTO = new UsuariosDTO();
        usuariosDTO.setId(1);
        usuariosDTO.setNome("Teste");
        usuariosDTO.setEmail("email@email.com");
        usuariosDTO.setSenha("senha123");

        Usuarios up = new Usuarios();
        up.setId(1);
        up.setNome("Teste");
        up.setEmail("email@email.com");
        up.setSenha("senha");

        when(repository.getOne(1)).thenReturn(up);

        UsuariosDTO test = facade.atualizar(usuariosDTO, 1);

        verify(repository, times(1)).save(any());
        assertThat(usuariosDTO.getSenha()).isEqualTo(up.getSenha());

    }

    @Test
    void buscarPorId() {
        Usuarios test = new Usuarios();
        test.setId(1);
        test.setNome("Teste");
        test.setEmail("email@email.com");
        test.setSenha("senha");

        when(repository.getOne(1)).thenReturn(test);
        UsuariosDTO test2 = facade.buscarPorId(1);

        verify(repository, times(1)).getOne(1);
        assertThat(test2.getNome()).isEqualTo(test.getNome());
        assertThat(test2.getEmail()).isEqualTo(test.getEmail());



    }

    @Test
    void buscarTodos() {

        Usuarios data = new Usuarios();
        data.setId(1);
        data.setNome("Teste");
        data.setEmail("email@email.com");
        data.setSenha("senha");

        Usuarios data2 = new Usuarios();
        data.setId(2);
        data.setNome("Teste1");
        data.setEmail("email1@email.com");
        data.setSenha("senha1");

        List<Usuarios> list = new ArrayList<Usuarios>();
        list.add(data);
        list.add(data2);

        when(repository.findAll()).thenReturn(list);

        List<UsuariosDTO> test = facade.buscarTodos();

        verify(repository, times(1)).findAll();
        assertThat(test.size()).isEqualTo(2);


    }

    @Test
    void remover() {
        String test = "Removido com sucesso";

        String result = facade.remover(1);
        assertThat(result).isEqualTo(test);
    }

    @Test
    void login() {
        String sucesso = "Login efetuado com sucesso";

        UsuariosDTO usuariosDTO = new UsuariosDTO();
        usuariosDTO.setId(1);
        usuariosDTO.setNome("Teste");
        usuariosDTO.setEmail("email@email.com");
        usuariosDTO.setSenha("senha123");

        Usuarios data = new Usuarios();
        data.setId(1);
        data.setNome("Teste");
        data.setEmail("email@email.com");
        data.setSenha("senha123");

        when(repository.findByEmailandSenha(usuariosDTO.getEmail(), usuariosDTO.getSenha())).thenReturn(data);

        String result = facade.login(usuariosDTO);

        assertThat(result).isEqualTo(sucesso);
    }

    @Test
    void loginError() {

        String falha = "Usuário ou senha incorretos!";

        UsuariosDTO usuariosDTO = new UsuariosDTO();
        usuariosDTO.setId(1);
        usuariosDTO.setNome("Teste");
        usuariosDTO.setEmail("email@email.com");
        usuariosDTO.setSenha("senha123");


        when(repository.findByEmailandSenha(usuariosDTO.getEmail(), usuariosDTO.getSenha())).thenReturn(null);

        String result = facade.login(usuariosDTO);

        assertThat(result).isEqualTo(falha);
    }
}