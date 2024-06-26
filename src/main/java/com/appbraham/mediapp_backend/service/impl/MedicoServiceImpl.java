package com.appbraham.mediapp_backend.service.impl;

import com.appbraham.mediapp_backend.model.Medico;
import com.appbraham.mediapp_backend.repo.IGenericRepository;
import com.appbraham.mediapp_backend.repo.IMedicoRepo;
import com.appbraham.mediapp_backend.service.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoServiceImpl extends GenericServiceImpl<Medico, Integer> implements IMedicoService {

    @Autowired
    private IMedicoRepo repo;

    @Override
    protected IGenericRepository<Medico, Integer> getRepo() {
        //Esto funciona para saber cual repository usar en el genérico gracias al @Autowired
        return repo;
    }

//    @Override
//    public Medico registrar(Medico obj) {
//        return repo.save(obj);
//    }
//
//    @Override
//    public Medico modificar(Medico obj) {
//        return repo.save(obj);
//    }
//
//    @Override
//    public List<Medico> listar() {
//        return repo.findAll();
//    }
//
//    @Override
//    public Medico listarPorId(Integer id) {
//        Optional<Medico> op = repo.findById(id);
//        return op.isPresent() ? op.get() : new Medico();
//    }
//
//    @Override
//    public boolean eliminar(Integer id) {
//        repo.deleteById(id);
//        return true;
//    }
}
