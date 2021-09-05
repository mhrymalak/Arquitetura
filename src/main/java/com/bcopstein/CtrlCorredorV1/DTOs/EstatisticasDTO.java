package com.bcopstein.CtrlCorredorV1.DTOs;

import com.bcopstein.CtrlCorredorV1.Entities.Evento;

import java.time.LocalTime;
import java.util.List;

public class EstatisticasDTO{

    public LocalTime media;
    public LocalTime mediana;
    public LocalTime desvioPadrao;
    public List<Evento> eventos;

    public EstatisticasDTO(LocalTime media, LocalTime mediana, LocalTime desvioPadrao, List<Evento>eventos) {
        
        this.media = media;
        this.mediana = mediana;
        this.desvioPadrao = desvioPadrao;
        this.eventos = eventos;
    }
}

