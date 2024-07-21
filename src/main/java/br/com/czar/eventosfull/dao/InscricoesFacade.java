package br.com.czar.eventosfull.dao;

import br.com.czar.eventosfull.models.*;
import br.com.czar.eventosfull.util.InscricaoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InscricoesFacade {
    @Autowired
    private InscricoesRepository repository;
    private UsuariosFacade usuariosRepository;

    public InscricoesDTO criar(InscricoesDTO inscricoesDTO) {
        Inscricoes inscricoes = new Inscricoes();
        inscricoes.setId(inscricoesDTO.getId());
        inscricoes.setIdUser(inscricoesDTO.getIdUser());
        inscricoes.setIdEvent(inscricoesDTO.getIdEvent());
        inscricoes.setStatus(InscricaoStatus.NEW.ordinal());
        repository.save(inscricoes);
        inscricoesDTO.setId(inscricoes.getId());
        inscricoesDTO.setStatus(inscricoes.getStatus());
        return inscricoesDTO;
    }

    public InscricoesDTO atualizar(InscricoesDTO inscricoesDTO, int status) {
        Inscricoes inscricoes = repository.findByUserandEvent(inscricoesDTO.getIdUser(), inscricoesDTO.getIdEvent());
        inscricoes.setStatus(status);
        repository.save(inscricoes);
        inscricoesDTO.setId(inscricoes.getId());
        inscricoesDTO.setStatus(status);
        return inscricoesDTO;
    }

    public InscricoesDTO buscarPorId(int id) {
        Inscricoes inscricoes = repository.getOne(id);
        InscricoesDTO inscricoesDTO = new InscricoesDTO();
        inscricoesDTO.setId(inscricoes.getId());
        inscricoesDTO.setIdUser(inscricoes.getIdUser());
        inscricoesDTO.setIdEvent(inscricoes.getIdEvent());
        inscricoesDTO.setStatus(inscricoes.getStatus());
        return inscricoesDTO;
    }

    public List<InscricoesDTO> buscarTodos() {
        List<Inscricoes> inscricoes = repository.findAll();
        List<InscricoesDTO> inscricaoDTOs = new ArrayList<>();
        for (Inscricoes inscricao : inscricoes) {
            InscricoesDTO inscricoesDTO = new InscricoesDTO();
            inscricoesDTO.setId(inscricao.getId());
            inscricoesDTO.setIdUser(inscricao.getIdUser());
            inscricoesDTO.setIdEvent(inscricao.getIdEvent());
            inscricoesDTO.setStatus(inscricao.getStatus());
            inscricaoDTOs.add(inscricoesDTO);
        }
        return inscricaoDTOs;
    }

    public String remover(int id) {
        repository.deleteById(id);
        return "Removido com sucesso";
    }
}