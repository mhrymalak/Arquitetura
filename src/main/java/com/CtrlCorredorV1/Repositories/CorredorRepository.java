package com.CtrlCorredorV1.Repositories;

import com.CtrlCorredorV1.Entities.Corredor;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorredorRepository {

    List<Corredor> consultaCorredor();
    boolean cadastraCorredor(Corredor corredor);
}
