package com.CtrlCorredorV1.Repositories;

import com.CtrlCorredorV1.Entities.Evento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository {
    List<Evento> findByDistancia(int distancia);
    List<Evento> findByDistanciaAndAno(int distancia, short ano);
    List<Evento> findAll();
    boolean addEvento(Evento evento);
}
