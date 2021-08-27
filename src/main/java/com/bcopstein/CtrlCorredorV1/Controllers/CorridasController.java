package com.bcopstein.CtrlCorredorV1.Controllers;

import java.util.List;

import com.bcopstein.CtrlCorredorV1.Entities.Corredor;
import com.bcopstein.CtrlCorredorV1.Entities.EstatísticasDTO;
import com.bcopstein.CtrlCorredorV1.Entities.Evento;
import com.bcopstein.CtrlCorredorV1.Repositories.CorredorRepository;
import com.bcopstein.CtrlCorredorV1.Repositories.EventoRepository;
import com.bcopstein.CtrlCorredorV1.Services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ctrlCorridas")
public class CorridasController {

    private final CorredorRepository corredorRepository;
    private final EventoService eventoService;

    @Autowired
    public CorridasController(CorredorRepository repository, EventoService eventoService) {
        this.corredorRepository = repository;
        this.eventoService = eventoService;
    }

    @GetMapping("/corredor")
    @CrossOrigin(origins = "*")
    public List<Corredor> consultaCorredor() {
        return corredorRepository.consultaCorredor();
    }

    @PostMapping("/corredor")
    @CrossOrigin(origins = "*")
    public boolean cadastraCorredor(@RequestBody final Corredor corredor) {
        return corredorRepository.cadastraCorredor(corredor);
    }

    @GetMapping("/eventos")
    @CrossOrigin(origins = "*")
    public List<Evento> consultaEventos() {
        return eventoService.findAll();
    }

    @PostMapping("/eventos") // adiciona evento no único corredor
    @CrossOrigin(origins = "*")
    public boolean informaEvento(@RequestBody final Evento evento) {
        return eventoService.addEvento(evento);
    }

    @GetMapping("/estatisticas")
    @CrossOrigin(origins = "*")
    public EstatísticasDTO estatisticas(@RequestParam final int distancia){
        return eventoService.estatisticas(distancia);
    }
}
