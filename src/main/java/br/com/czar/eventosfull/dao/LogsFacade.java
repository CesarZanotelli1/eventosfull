package br.com.czar.eventosfull.dao;

import br.com.czar.eventosfull.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class LogsFacade {
    @Autowired
    private LogsRepository repository;

    public LogsDTO criar(LogsDTO logsDTO){
        Logs logs = new Logs();
        logs.setId(logsDTO.getId());
        logs.setDescricao(logsDTO.getDescricao());
        logs.setData(String.valueOf(new Date()));
        repository.save(logs);
        logsDTO.setId(logs.getId());
        return logsDTO;
    }
}

