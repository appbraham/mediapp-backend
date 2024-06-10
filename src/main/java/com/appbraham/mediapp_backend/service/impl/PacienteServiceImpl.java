package com.appbraham.mediapp_backend.service.impl;

import com.appbraham.mediapp_backend.model.Paciente;
import com.appbraham.mediapp_backend.repo.IPacienteRepo;
import com.appbraham.mediapp_backend.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IPacienteService {

    @Autowired
    private IPacienteRepo repo;

    @Override
    public Paciente registrar(Paciente obj) {
        return repo.save(obj);
    }

    @Override
    public Paciente modificar(Paciente obj) {
        return repo.save(obj);
    }

    @Override
    public List<Paciente> listar() {
        return repo.findAll();
    }

    @Override
    public Paciente listarPorId(Integer id) {
        Optional<Paciente> op = repo.findById(id);
        return op.isPresent() ? op.get() : new Paciente();
    }

    @Override
    public boolean eliminar(Integer id) {
        repo.deleteById(id);
        return true;
    }
}
