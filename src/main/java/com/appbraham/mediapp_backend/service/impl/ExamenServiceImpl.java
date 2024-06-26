package com.appbraham.mediapp_backend.service.impl;

import com.appbraham.mediapp_backend.model.Examen;
import com.appbraham.mediapp_backend.model.Medico;
import com.appbraham.mediapp_backend.repo.IExamenRepo;
import com.appbraham.mediapp_backend.repo.IGenericRepository;
import com.appbraham.mediapp_backend.service.IExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenServiceImpl extends GenericServiceImpl<Examen, Integer> implements IExamenService {

    @Autowired
    private IExamenRepo repo;

    @Override
    protected IGenericRepository<Examen, Integer> getRepo() {
        //Esto funciona para saber cual repository usar en el genérico gracias al @Autowired
        return repo;
    }

//    @Override
//    public Examen registrar(Examen obj) {
//        return repo.save(obj);
//    }
//
//    @Override
//    public Examen modificar(Examen obj) {
//        return repo.save(obj);
//    }
//
//    @Override
//    public List<Examen> listar() {
//        return repo.findAll();
//    }
//
//    @Override
//    public Examen listarPorId(Integer id) {
//        Optional<Examen> op = repo.findById(id);
//        return op.isPresent() ? op.get() : new Examen();
//    }
//
//    @Override
//    public boolean eliminar(Integer id) {
//        repo.deleteById(id);
//        return true;
//    }
}
