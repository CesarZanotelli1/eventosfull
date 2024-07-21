package br.com.czar.eventosfull.dao;

import br.com.czar.eventosfull.models.Eventos;

import org.springframework.data.jpa.repository.JpaRepository;


public interface EventosRepository extends JpaRepository<Eventos, Integer> {
}

