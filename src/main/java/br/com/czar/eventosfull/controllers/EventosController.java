package br.com.czar.eventosfull.controllers;

import br.com.czar.eventosfull.dao.EventosFacade;
import br.com.czar.eventosfull.dao.LogsFacade;
import br.com.czar.eventosfull.models.EventosDTO;
import br.com.czar.eventosfull.models.LogsDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/eventosfull/eventos", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventosController {
    @Autowired
    private EventosFacade eventosFacade;
    @Autowired
    HttpServletRequest request;
    @Autowired
    private LogsFacade logsFacade;


    @PostMapping
    @ResponseBody
    public EventosDTO addEvento(@RequestBody EventosDTO eventosDTO) {
        LogsDTO logsDTO = new LogsDTO();
        logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        //logsFacade.criar(logsDTO);
        return eventosFacade.criar(eventosDTO);
    }

    @PutMapping("/{eventoId}")
    @ResponseBody
    public EventosDTO updateEvento(@PathVariable("eventoId") int eventoId, @RequestBody EventosDTO eventosDTO) {
       // LogsDTO logsDTO = new LogsDTO();
        //logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        //logsFacade.criar(logsDTO);

        return eventosFacade.atualizar(eventosDTO, eventoId);
    }


    @GetMapping
    @ResponseBody
    public List<EventosDTO> getAllEventos() {
       // LogsDTO logsDTO = new LogsDTO();
        //logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        //logsFacade.criar(logsDTO);

        return eventosFacade.buscarTodos();
    }

    @GetMapping("/{eventoId}")
    @ResponseBody
    public EventosDTO getEventoById(@PathVariable("eventoId") int eventoId) {
      //  LogsDTO logsDTO = new LogsDTO();
       // logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        //logsFacade.criar(logsDTO);

        return eventosFacade.buscarPorId(eventoId);
    }

    @DeleteMapping("/{eventoId}")
    @ResponseBody
    public String deleteEventoById(@PathVariable("eventoId") int eventoId) {
       // LogsDTO logsDTO = new LogsDTO();
       // logsDTO.setDescricao(request.getMethod()+" "+request.getRequestURI());
        //logsFacade.criar(logsDTO);
        return eventosFacade.remover(eventoId);
    }

}
