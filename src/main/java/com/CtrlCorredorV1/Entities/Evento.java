package com.CtrlCorredorV1.Entities;

import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.CtrlCorredorV1.Tools.Helper.formato;

public class Evento implements Comparable<LocalTime> {
    private int id;
    private String nome;
    // Distancia percorrida
    private int distancia; // metros
    // Data e tempo que o corredor levou para percorrer a distancia
    private LocalDate data;
    private LocalTime tempo;

    public Evento(String nome, byte dia, byte mes, short ano, int distancia, byte horas, byte minutos, byte segundos) {
        this.nome = nome;
        this.data = LocalDate.of(ano, mes, dia);
        this.tempo = LocalTime.of(horas, minutos, segundos);
        this.distancia = distancia;
    }
    
    public Evento(int id, String nome, Date date, int distancia, Time tempo) {
        this.id = id;
        this.nome = nome;
        this.data = date.toLocalDate();
        this.tempo = tempo.toLocalTime();
        this.distancia = distancia;
    }

    public int getSegundoDoTempo() {
        return tempo.toSecondOfDay();
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getTempo() {
        return tempo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }


    public int getDistancia() {
        return distancia;
    }

    @Override
    public String toString() {
        return "Evento [id= " + getId() + ", nome=" + getNome() +", data= "+getTempo().format(formato) +", tempo=" + getTempo().toString() +", distancia=" + distancia +  "]";
    }

    @Override
    public int compareTo(LocalTime o) {
        return this.tempo.toSecondOfDay() - o.toSecondOfDay();
    }
}
