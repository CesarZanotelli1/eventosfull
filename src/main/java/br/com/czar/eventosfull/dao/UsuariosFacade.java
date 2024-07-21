package br.com.czar.eventosfull.dao;

import br.com.czar.eventosfull.models.Usuarios;
import br.com.czar.eventosfull.models.UsuariosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuariosFacade {
    @Autowired
    private UsuariosRepository repository;

        public UsuariosDTO criar(UsuariosDTO usuariosDTO){
            Usuarios usuarios = new Usuarios();
            usuarios.setId(usuariosDTO.getId());
            usuarios.setEmail(usuariosDTO.getEmail());
            usuarios.setNome(usuariosDTO.getNome());
            usuarios.setSenha(usuariosDTO.getSenha());
            repository.save(usuarios);
            return usuariosDTO;
    }

    public UsuariosDTO atualizar(UsuariosDTO usuariosDTO, Integer id){
            Usuarios usuarios = repository.getOne(id);
            usuarios.setId(usuariosDTO.getId());
            usuarios.setEmail(usuariosDTO.getEmail());
            usuarios.setNome(usuariosDTO.getNome());
            usuarios.setSenha(usuariosDTO.getSenha());
            repository.save(usuarios);
            return usuariosDTO;
    }

    public UsuariosDTO buscarPorId(Integer id){
            Usuarios usuarios = repository.getOne(id);
            UsuariosDTO usuariosDTO = new UsuariosDTO();
            usuariosDTO.setId(usuarios.getId());
            usuariosDTO.setEmail(usuarios.getEmail());
            usuariosDTO.setNome(usuarios.getNome());
            usuariosDTO.setSenha(usuarios.getSenha());
            return usuariosDTO;
    }
    public List<UsuariosDTO> buscarTodos() {
            List<Usuarios> usuarios = repository.findAll();
            List<UsuariosDTO> usuariosDTOs = new ArrayList<>();
            for (Usuarios usuario : usuarios){
                UsuariosDTO usuariosDTO = new UsuariosDTO();
                usuariosDTO.setId(usuario.getId());
                usuariosDTO.setEmail(usuario.getEmail());
                usuariosDTO.setNome(usuario.getNome());
                usuariosDTO.setSenha(usuario.getSenha());
                usuariosDTOs.add(usuariosDTO);
            }
            return usuariosDTOs;
    }

    public String remover(Integer id){
            repository.deleteById(id);
            return "Removido com sucesso";
    }

    public String login(UsuariosDTO usuariosDTO){
            Usuarios usuarios = repository.findByEmailandSenha(usuariosDTO.getEmail(), usuariosDTO.getSenha());
            if(usuarios == null){
                return ("Usuário ou senha incorretos!");
            }
            return ("Login efetuado com sucesso");

    }
}
