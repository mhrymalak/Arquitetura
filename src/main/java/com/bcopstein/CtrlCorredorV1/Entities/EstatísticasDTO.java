package com.bcopstein.CtrlCorredorV1.Entities;

import java.time.LocalTime;
import java.util.List;

public class EstatísticasDTO {

    public LocalTime media;
    public double mediana;
    public double desvioPadrao;
    public List<Evento> eventos;

    public EstatísticasDTO(LocalTime media, double mediana, double desvioPadrao, List<Evento>eventos) {
        this.media = media;
        this.mediana = mediana;
        this.desvioPadrao = desvioPadrao;
        this.eventos = eventos;
    }
}
