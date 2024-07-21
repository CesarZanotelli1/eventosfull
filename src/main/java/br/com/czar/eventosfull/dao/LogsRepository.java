package br.com.czar.eventosfull.dao;

import br.com.czar.eventosfull.models.Eventos;
import br.com.czar.eventosfull.models.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends JpaRepository<Logs, Integer> {
}
