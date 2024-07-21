package br.com.czar.eventosfull.controllers;

import br.com.czar.eventosfull.dao.InscricoesFacade;
import br.com.czar.eventosfull.dao.LogsFacade;
import br.com.czar.eventosfull.dao.UsuariosFacade;
import br.com.czar.eventosfull.models.InscricoesDTO;
import br.com.czar.eventosfull.models.Logs;
import br.com.czar.eventosfull.models.LogsDTO;
import br.com.czar.eventosfull.models.UsuariosDTO;
import br.com.czar.eventosfull.util.InscricaoStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/eventosfull/inscricoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class InscricoesController {
    @Autowired
    private InscricoesFacade inscricoesFacade;
    @Autowired
    HttpServletRequest request;
    @Autowired
    private LogsFacade logsFacade;

    @PostMapping
    @ResponseBody
    public InscricoesDTO addEvento(@RequestBody InscricoesDTO inscricoesDTO) {
        LogsDTO logsDTO = new LogsDTO();
        logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        logsFacade.criar(logsDTO);
        return inscricoesFacade.criar(inscricoesDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public InscricoesDTO updateEvento(@PathVariable("id") Integer id, @RequestBody InscricoesDTO inscricoesDTO) {
        LogsDTO logsDTO = new LogsDTO();
        logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        logsFacade.criar(logsDTO);
        return inscricoesFacade.atualizar(inscricoesDTO, id);
    }

    @GetMapping
    @ResponseBody
    public List<InscricoesDTO> getAllEventos() {
        LogsDTO logsDTO = new LogsDTO();
        logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        logsFacade.criar(logsDTO);
        return inscricoesFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public InscricoesDTO getEventoById(@PathVariable("id") Integer id) {
        LogsDTO logsDTO = new LogsDTO();
        logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        logsFacade.criar(logsDTO);
        return inscricoesFacade.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteEventoById(@PathVariable("id") Integer id) {
        LogsDTO logsDTO = new LogsDTO();
        logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        logsFacade.criar(logsDTO);
        return inscricoesFacade.remover(id);
    }

    @PostMapping("/check-in")
    @ResponseBody
    public InscricoesDTO checkIn(@RequestBody InscricoesDTO data) {
        LogsDTO logsDTO = new LogsDTO();
        logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        logsFacade.criar(logsDTO);
        return inscricoesFacade.atualizar(data, InscricaoStatus.CHECK_IN_DONE.ordinal());
    }

    @PostMapping("/confirme")
    @ResponseBody
    public InscricoesDTO confirme(@RequestBody InscricoesDTO data) {
        LogsDTO logsDTO = new LogsDTO();
        logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        logsFacade.criar(logsDTO);
        return inscricoesFacade.atualizar(data, InscricaoStatus.CONFIRMED.ordinal());
    }

    @PostMapping("/cancel")
    @ResponseBody
    public InscricoesDTO cancel(@RequestBody InscricoesDTO data) {
        LogsDTO logsDTO = new LogsDTO();
        logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        logsFacade.criar(logsDTO);
        return inscricoesFacade.atualizar(data, InscricaoStatus.CANCELED.ordinal());
    }
}
