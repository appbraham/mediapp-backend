package com.appbraham.mediapp_backend.service.impl;

import com.appbraham.mediapp_backend.model.Especialidad;
import com.appbraham.mediapp_backend.repo.IEspecialidadRepo;
import com.appbraham.mediapp_backend.service.IEspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService {

    @Autowired
    private IEspecialidadRepo repo;

    @Override
    public Especialidad registrar(Especialidad obj) {
        return repo.save(obj);
    }

    @Override
    public Especialidad modificar(Especialidad obj) {
        return repo.save(obj);
    }

    @Override
    public List<Especialidad> listar() {
        return repo.findAll();
    }

    @Override
    public Especialidad listarPorId(Integer id) {
        Optional<Especialidad> op = repo.findById(id);
        return op.isPresent() ? op.get() : new Especialidad();
    }

    @Override
    public boolean eliminar(Integer id) {
        repo.deleteById(id);
        return true;
    }
}
