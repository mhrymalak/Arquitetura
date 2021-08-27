package com.bcopstein.CtrlCorredorV1.Entities;

import java.time.LocalDateTime;

public class Evento {
    private int id;
    private String nome;
    // Distancia percorrida
    private int distancia; // metros
    // Data e tempo que o corredor levou para percorrer a distancia
    private LocalDateTime dateTime;
    
    public Evento(int id,String nome, int dia, int mes, int ano, int distancia, int horas, int minutos, int segundos) {
        this.id = id;
        this.nome = nome;
        this.dateTime = LocalDateTime.of(ano, mes, dia, horas, minutos, segundos);
        this.distancia = distancia;
    }

    public long getSecondsFromTime() {
        return dateTime.toLocalTime().toSecondOfDay();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getDia() {
        return dateTime.getDayOfMonth();
    }

    public int getMes() {
        return dateTime.getMonthValue();
    }

    public int getAno() {
        return dateTime.getYear();
    }

    public int getDistancia() {
        return distancia;
    }

    public int getHoras() {
        return dateTime.getHour();
    }

    public int getMinutos() {
        return dateTime.getMinute();
    }

    public int getSegundos() {
        return dateTime.getSecond();
    }

    @Override
    public String toString() {
        return "Evento [ano=" + getAno() + ", dia=" + getDia() + ", distancia=" + distancia + ", horas=" + getHoras() + ", id=" + id
                + ", mes=" + getMes() + ", minutos=" + getMinutos() + ", nome=" + nome + ", segundos=" + getSegundos() + "]";
    }
}
