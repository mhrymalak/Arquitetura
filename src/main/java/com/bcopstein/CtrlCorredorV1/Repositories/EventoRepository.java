package com.bcopstein.CtrlCorredorV1.Repositories;

import com.bcopstein.CtrlCorredorV1.Entities.Evento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository {
    List<Evento> findByDistancia(int distancia);
    List<Evento> findAll();
    boolean addEvento(Evento evento);
}
