package com.CtrlCorredorV1.Services;

import com.CtrlCorredorV1.Entities.Corredor;
import com.CtrlCorredorV1.Repositories.CorredorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorredorService {

    private final CorredorRepository corredorRepository;

    @Autowired
    public CorredorService(CorredorRepository corredorRepository) {
        this.corredorRepository = corredorRepository;
    }

    public List<Corredor> consultaCorredor() {
        return corredorRepository.consultaCorredor();
    }

    public boolean cadastraCorredor(Corredor corredor) {
        return corredorRepository.cadastraCorredor(corredor);
    }
}
