package com.CtrlCorredorV1.Controllers;

import java.util.List;

import com.CtrlCorredorV1.Entities.Corredor;
import com.CtrlCorredorV1.Entities.ErrorResponse;
import com.CtrlCorredorV1.DTOs.EstatisticasDTO;
import com.CtrlCorredorV1.Entities.Evento;
import com.CtrlCorredorV1.DTOs.PerformanceDTO;
import com.CtrlCorredorV1.Services.CorredorService;
import com.CtrlCorredorV1.Services.EventoService;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ctrlCorridas")
public class CorridasController {

    private final CorredorService corredorService;
    private final EventoService eventoService;
    private final Gson jsonParser;
    @Autowired
    public CorridasController(CorredorService corredorService, EventoService eventoService) {
        this.corredorService = corredorService;
        this.eventoService = eventoService;
        this.jsonParser = new Gson();
    }

    @GetMapping("/corredor")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> consultaCorredor() {
        List<Corredor> serviceResult = corredorService.consultaCorredor();
        String response;

        if(serviceResult.size() > 0) {
            response = jsonParser.toJson(serviceResult);
            return ResponseEntity.ok().body(response);
        }
        ErrorResponse errorResponse = new ErrorResponse("consultaCorredor");
        errorResponse.AddError("Lista de Corredores vazia");
        response = jsonParser.toJson(errorResponse.getErrorResponse());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping("/corredor")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> cadastraCorredor(@RequestBody final Corredor corredor) {
        boolean response = corredorService.cadastraCorredor(corredor);

        return response ? ResponseEntity.status(HttpStatus.ACCEPTED).body("Corredor cadastrado") : 
            ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Corredor não cadastrado");
    }

    @GetMapping("/eventos")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> consultaEventos() {
        List<Evento> eventList =  eventoService.findAll();
        String response;
        
        if(eventList.size() > 0){
            response = jsonParser.toJson(eventList);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        ErrorResponse errorResponse = new ErrorResponse("consultaEventos");
        errorResponse.AddError("Lista de Eventos vazia");
        response = jsonParser.toJson(errorResponse);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping("/eventos") // adiciona evento no único corredor
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> informaEvento(@RequestBody final Evento evento) {
        boolean response = eventoService.addEvento(evento);
        
            return response ? ResponseEntity.status(HttpStatus.ACCEPTED).body("Evento cadastrado") : 
            ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Evento não cadastrado");
    }

    @GetMapping("/estatisticas")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> estatisticas(@RequestParam final int distancia){
        EstatisticasDTO serviceResponse = eventoService.estatisticas(distancia);
        String response = "";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse error = new ErrorResponse("estatisticas");
        
        if(serviceResponse == null){
            error.AddError("Distancia inválida ou nenhum evento foi encontrado");
            response = jsonParser.toJson(error.getErrorResponse());
        }
        else if(serviceResponse.desvioPadrao == null && serviceResponse.mediana == null){
                    error.AddError("\"Media\": " + serviceResponse.media);
                    error.AddError("Só uma participação em eventos");
                    response = jsonParser.toJson(error.getErrorResponse());
        }
        
        if(response.equals("")){
            response = jsonParser.toJson(serviceResponse);
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/aumentoPerformance")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> aumentoPerformance(@RequestParam final short distancia, @RequestParam final short ano) {
        PerformanceDTO serviceResponse = eventoService.aumentoPerformance(distancia, ano);
        String response;

        if(serviceResponse != null){
            response = jsonParser.toJson(serviceResponse);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        ErrorResponse error = new ErrorResponse("aumentoPerformance");
        error.AddError("Lista de Eventos vazia ou com um único evento");
        response = jsonParser.toJson(error.getErrorResponse());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
