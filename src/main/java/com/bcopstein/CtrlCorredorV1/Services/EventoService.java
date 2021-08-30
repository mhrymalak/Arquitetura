package com.bcopstein.CtrlCorredorV1.Services;

import com.bcopstein.CtrlCorredorV1.DTOs.EstatísticasDTO;
import com.bcopstein.CtrlCorredorV1.Entities.Evento;
import com.bcopstein.CtrlCorredorV1.DTOs.PerformanceDTO;
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

    public EstatísticasDTO estatisticas(int distancia) {
        List<Evento> eventos = eventoRepository.findByDistancia(distancia);

        LocalTime media = media(eventos);
        double mediana = mediana(eventos);
        double desvioPadrao = desvioPadrao(eventos);

        return new EstatísticasDTO(media, mediana, desvioPadrao, eventos);
    }

    private LocalTime media(List<Evento> eventos) {
        long segundos = 0;
        for (Evento e : eventos) {
            segundos += e.getSecondsFromTime();
        }
        segundos /= eventos.size();
        return LocalTime.ofSecondOfDay(segundos);
    }

    private double mediana(List<Evento> eventos) {

        return 0.0;
    }

    private double desvioPadrao(List<Evento> eventos) {

        return 0.0;
    }

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    public boolean addEvento(Evento evento) {
        return eventoRepository.addEvento(evento);
    }

    public PerformanceDTO aumentoPerformance(int distancia, int ano) {
        List<Evento> eventos = eventoRepository.findByDistanciaAndAno(distancia, ano);
        eventos.sort((date1, date2) -> date1.compareTo(date2.getData()));
        return processaListaDeEventos(eventos);
    }

    private PerformanceDTO processaListaDeEventos(List<Evento> eventos) {
        String evento1 = "", evento2 = "", aux1 = "", aux2;
        int melhorPerformance = 0;
        for (Evento e : eventos) {

            // == lógica está errada ==
            if (melhorPerformance < 1) {
                melhorPerformance = e.getTimeInSeconds();
                aux1 = e.getNome();
                continue;
            }
            aux2 = aux1;
            aux1 = e.getNome();

            // == lógica está errada ==
            int aux = melhorPerformance - e.getTimeInSeconds();
            if (aux > melhorPerformance) {
                melhorPerformance = aux;
                evento1 = aux1;
                evento2 = aux2;
            }
        }
        return new PerformanceDTO(evento1, evento2, melhorPerformance);
    }
}
