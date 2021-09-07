package com.bcopstein.CtrlCorredorV1.Entities;

import java.time.LocalDate;

public class Corredor {
    private String cpf;
    private String nome;
    private LocalDate data;
    private String genero;

    public Corredor(String cpf, String nome, byte dia, byte mes, short ano, String genero) {
        this.cpf = cpf;
        this.nome = nome;
        this.data = LocalDate.of(ano,mes,dia);
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

}