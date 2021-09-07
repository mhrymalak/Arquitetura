package com.CtrlCorredorV1.Entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Corredor {
    private String cpf;
    private String nome;
    private LocalDate data;
    private String genero;
    private static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public Corredor(String cpf, String nome, byte dia, byte mes, short ano, String genero) {
        this.cpf = cpf;
        this.nome = nome;
        this.data = LocalDate.of(ano,mes,dia);
        this.genero = genero;
    }

    public Corredor(String cpf, String nome, Date data, String genero) {
        this.cpf = cpf;
        this.nome = nome;
        this.data = data.toLocalDate();
        this.genero = genero;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getData() {
        return data;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "Corredor [id= " + getCpf() + ", nome=" + getNome() +", data= "+getData().format(formato) +", genero=" + getGenero() +  "]";
    }

}