package com.CtrlCorredorV1.Repositories;

import com.CtrlCorredorV1.Entities.Corredor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class CorredorRepositoryImpl implements CorredorRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CorredorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Corredor> consultaCorredor() {
        List<Corredor> resp = this.jdbcTemplate.queryForList("SELECT cpf,nome,data_corredor,genero from corredores FROM corredores", Corredor.class);
        return resp;
    }

    @Override
    public boolean cadastraCorredor(Corredor corredor) {
        // Limpa a base de dados
        this.jdbcTemplate.batchUpdate("DELETE * from Corredores");
        // Então cadastra o novo "corredor único"
        this.jdbcTemplate.update("INSERT INTO corredores(cpf,nome,data_corredor,genero) VALUES (?,?,?,?)",
                corredor.getCpf(), corredor.getNome(), Date.valueOf(corredor.getData()),
                corredor.getGenero());
        return true;
    }
}
