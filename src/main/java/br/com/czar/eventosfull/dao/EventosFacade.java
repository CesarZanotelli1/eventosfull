package br.com.czar.eventosfull.dao;

import br.com.czar.eventosfull.models.Eventos;
import br.com.czar.eventosfull.models.EventosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventosFacade {
    @Autowired
    private EventosRepository repository;

        public EventosDTO criar(EventosDTO eventosDTO){
            Eventos eventos = new Eventos();
            eventos.setId(eventosDTO.getId());
            eventos.setDescricao(eventosDTO.getDescricao());
            eventos.setData(eventosDTO.getData());
            repository.save(eventos);
            eventosDTO.setId(eventos.getId());
            return eventosDTO;
    }

    public EventosDTO atualizar(EventosDTO eventosDTO, int id){
        Eventos eventos = repository.getOne(id);
        eventos.setDescricao(eventosDTO.getDescricao());
        eventos.setData(eventosDTO.getData());
        repository.save(eventos);
        return eventosDTO;
    }

    public EventosDTO buscarPorId(int id){
            Eventos eventos = repository.getOne(id);
            EventosDTO eventosDTO = new EventosDTO();
            eventosDTO.setId(eventos.getId());
            eventosDTO.setDescricao(eventos.getDescricao());
            eventosDTO.setData(eventos.getData());
            return eventosDTO;
    }
    public List<EventosDTO> buscarTodos() {
            List<Eventos> eventos = repository.findAll();
            List<EventosDTO> eventosDTOs = new ArrayList<>();
            for (Eventos evento : eventos){
                EventosDTO eventosDTO = new EventosDTO();
                eventosDTO.setId(evento.getId());
                eventosDTO.setDescricao(evento.getDescricao());
                eventosDTO.setData(evento.getData());
                eventosDTOs.add(eventosDTO);
            }
            return eventosDTOs;
    }

    public String remover(int id){
            repository.deleteById(id);
            return "Removido com sucesso";
    }
}
