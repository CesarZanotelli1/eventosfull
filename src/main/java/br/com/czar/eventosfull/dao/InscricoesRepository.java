package br.com.czar.eventosfull.dao;

import br.com.czar.eventosfull.models.Eventos;
import br.com.czar.eventosfull.models.Inscricoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscricoesRepository extends JpaRepository<Inscricoes, Integer> {
    @Query("from Inscricoes t where t.idUser = :usuario and t.idEvent = :evento")
    Inscricoes findByUserandEvent(Integer usuario, Integer evento);
}
