package com.bcopstein.CtrlCorredorV1.Services;

import com.bcopstein.CtrlCorredorV1.DTOs.EstatisticasDTO;
import com.bcopstein.CtrlCorredorV1.DTOs.PerformanceDTO;
import com.bcopstein.CtrlCorredorV1.Entities.Evento;
import com.bcopstein.CtrlCorredorV1.Repositories.EventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public EstatisticasDTO estatisticas(int distancia) {
        List<Evento> eventos = eventoRepository.findByDistancia(distancia);
        eventos.sort((x, y) -> x.compareTo(y.getTempo()));
        LocalTime media = eventos.size() > 0 ? media(eventos) : null;
        LocalTime mediana = null;
        LocalTime desvioPadrao = null;

        if(media == null)
            return null;
        else if(eventos.size() > 1){
            mediana = mediana(eventos);
            desvioPadrao = desvioPadrao(eventos, media.toSecondOfDay());
        }
        return new EstatisticasDTO(media, mediana, desvioPadrao, eventos);
    }

    private LocalTime media(List<Evento> eventos) {
        long segundos = 0;
        for (Evento e : eventos) {
            segundos += e.getSegundoDoTempo();
        }
        segundos /= eventos.size();
        return LocalTime.ofSecondOfDay(segundos);
    }
    
    private LocalTime mediana(List<Evento> eventos) {
        int divisao = eventos.size() / 2;
        LocalTime mediana;
        Evento evento1 = eventos.get(divisao - 1);
        Evento evento2 = eventos.get(divisao);

        if(eventos.size() % 2 == 0)
            mediana = LocalTime.ofSecondOfDay((evento1.getSegundoDoTempo() + evento2.getSegundoDoTempo()) / 2);
        else
            mediana = evento2.getTempo();

        return mediana;
    }

    private LocalTime desvioPadrao(List<Evento> eventos, int media) {
        double desvio = 0;
        int desvioFinal;
        for (Evento evento : eventos) {
            desvio += Math.pow(evento.getSegundoDoTempo() - media, 2);
        }
        desvio /= eventos.size();
        desvio = Math.sqrt(desvio);
        desvioFinal = (int)(desvio % 2 == 0 ? Math.ceil(desvio) : Math.floor(desvio));
        return LocalTime.ofSecondOfDay(desvioFinal);
    }

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    public boolean addEvento(Evento evento) {
        return eventoRepository.addEvento(evento);
    }

    public PerformanceDTO aumentoPerformance(int distancia, int ano) {
        List<Evento> eventos = eventoRepository.findByDistanciaAndAno(distancia, ano);
        if(eventos.size() > 1) {
            eventos.sort((evento1, evento2) -> evento1.compareTo(evento2.getTempo()));
            Evento evento1 = eventos.get(0);
            Evento evento2 = eventos.get(1);
            return new PerformanceDTO(evento1.getNome(), evento2.getNome(), evento1.getSegundoDoTempo() - evento2.getSegundoDoTempo());
        }
        return null;
    }
}
