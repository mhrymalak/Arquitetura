package com.bcopstein.CtrlCorredorV1.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Evento implements Comparable<LocalTime> {
    private int id;
    private String nome;
    // Distancia percorrida
    private int distancia; // metros
    // Data e tempo que o corredor levou para percorrer a distancia
    private LocalDate data;
    private LocalTime tempo;
    
    public Evento(int id,String nome, int dia, int mes, int ano, int distancia, int horas, int minutos, int segundos) {
        this.id = id;
        this.nome = nome;
        this.data = LocalDate.of(ano, mes, dia);
        this.tempo = LocalTime.of(horas, minutos, segundos);
        this.distancia = distancia;
    }

    public int getSecondsFromTime() {
        return tempo.toSecondOfDay();
    }

    public int getTimeInSeconds() {
        int time = 0;
        time += getHoras() * 3600;
        time += getMinutos() * 60;
        time += getSegundos();
        return time;
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

    public int getDia() {
        return data.getDayOfMonth();
    }

    public int getMes() {
        return data.getMonthValue();
    }

    public int getAno() {
        return data.getYear();
    }

    public int getDistancia() {
        return distancia;
    }

    public int getHoras() {
        return tempo.getHour();
    }

    public int getMinutos() {
        return tempo.getMinute();
    }

    public int getSegundos() {
        return tempo.getSecond();
    }

    @Override
    public String toString() {
        return "Evento [ano=" + getAno() + ", dia=" + getDia() + ", distancia=" + distancia + ", horas=" + getHoras() + ", id=" + id
                + ", mes=" + getMes() + ", minutos=" + getMinutos() + ", nome=" + nome + ", segundos=" + getSegundos() + "]";
    }

    @Override
    public int compareTo(LocalTime o) {
        return this.tempo.toSecondOfDay() - o.toSecondOfDay();
    }
}
