package com.CtrlCorredorV1.Repositories;

import com.CtrlCorredorV1.Entities.Evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public class EventoRepositoryImpl implements EventoRepository{

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public EventoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Evento> findByDistancia(int distancia) {
        List<Evento> resp = this.jdbcTemplate.queryForList("SELECT id, nome, data_evento, distancia, tempo FROM eventos WHERE distancia = :distancia", 
            Evento.class,   
            new MapSqlParameterSource()
                .addValue("distancia", distancia));

        return resp;
    }

    @Override
    public List<Evento> findByDistanciaAndAno(int distancia, short ano) {

        List<Evento> resp = this.jdbcTemplate.queryForList("SELECT id, nome, data_evento, distancia, tempo FROM eventos WHERE distancia = :distancia AND YEAR(data_evento) = :ano ",
        Evento.class,   
        new MapSqlParameterSource()
            .addValue("distancia", distancia)
            .addValue("ano",ano));
        return resp;
    }

    @Override
    public List<Evento> findAll() {
        return jdbcTemplate.queryForList("SELECT id, nome, data_evento, distancia, tempo FROM eventos", Evento.class);
    }

    @Override
    public boolean addEvento(Evento evento) {
        return this.jdbcTemplate.update(
            "INSERT INTO eventos(id, nome, data_evento, distancia, tempo) VALUES (?,?,?,?,?)",
            evento.getId(), evento.getNome(), Date.valueOf(evento.getData()),
            evento.getDistancia(), Time.valueOf(evento.getTempo())) == 1;
    }
}
