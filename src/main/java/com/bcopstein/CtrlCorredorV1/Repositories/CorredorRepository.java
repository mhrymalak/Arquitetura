package com.bcopstein.CtrlCorredorV1.Repositories;

import com.bcopstein.CtrlCorredorV1.Entities.Corredor;
import com.bcopstein.CtrlCorredorV1.Entities.Evento;

import java.util.List;

public interface CorredorRepository {

    List<Corredor> consultaCorredor();
    boolean cadastraCorredor(Corredor corredor);
}
