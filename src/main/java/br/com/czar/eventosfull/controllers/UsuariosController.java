package br.com.czar.eventosfull.controllers;

import br.com.czar.eventosfull.dao.EventosFacade;
import br.com.czar.eventosfull.dao.LogsFacade;
import br.com.czar.eventosfull.dao.UsuariosFacade;
import br.com.czar.eventosfull.models.EventosDTO;
import br.com.czar.eventosfull.models.LogsDTO;
import br.com.czar.eventosfull.models.UsuariosDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/eventosfull/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuariosController {
    @Autowired
    private UsuariosFacade usuariosFacade;
    @Autowired
    HttpServletRequest request;
    @Autowired
    private LogsFacade logsFacade;

    @PostMapping
    @ResponseBody
    public UsuariosDTO addEvento(@RequestBody UsuariosDTO usuariosDTO) {
        return usuariosFacade.criar(usuariosDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public UsuariosDTO updateEvento(@PathVariable("id") Integer id, @RequestBody UsuariosDTO usuariosDTO) {

        return usuariosFacade.atualizar(usuariosDTO, id);
    }

    @GetMapping
    @ResponseBody
    public List<UsuariosDTO> getAllEventos() {
        LogsDTO logsDTO = new LogsDTO();
        logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        logsFacade.criar(logsDTO);
        return usuariosFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UsuariosDTO getEventoById(@PathVariable("id") Integer id) {
        LogsDTO logsDTO = new LogsDTO();
        logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        logsFacade.criar(logsDTO);
        return usuariosFacade.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteEventoById(@PathVariable("id") Integer id) {
        LogsDTO logsDTO = new LogsDTO();
        logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        logsFacade.criar(logsDTO);
        return usuariosFacade.remover(id);
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody UsuariosDTO usuariosDTO) {
        LogsDTO logsDTO = new LogsDTO();
        logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        logsFacade.criar(logsDTO);
        return usuariosFacade.login(usuariosDTO);
    }
}
