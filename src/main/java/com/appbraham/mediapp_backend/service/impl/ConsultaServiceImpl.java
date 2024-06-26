package com.appbraham.mediapp_backend.service.impl;

import com.appbraham.mediapp_backend.dto.ConsultaListaExamenDTO;
import com.appbraham.mediapp_backend.model.Consulta;
import com.appbraham.mediapp_backend.repo.IConsultaExamenRepo;
import com.appbraham.mediapp_backend.repo.IConsultaRepo;
import com.appbraham.mediapp_backend.repo.IGenericRepository;
import com.appbraham.mediapp_backend.service.IConsultaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl extends GenericServiceImpl<Consulta, Integer> implements IConsultaService {

    @Autowired
    private IConsultaRepo repo;

    @Autowired
    private IConsultaExamenRepo consultaExamenRepo;

    @Override
    protected IGenericRepository<Consulta, Integer> getRepo() {
        return repo;
    }

    //Con Transactional se debe de cumplir todas las sentencias del método o clase donde se use.
    @Transactional
    @Override
    public Consulta registrarTransaccional(ConsultaListaExamenDTO dto) {
        dto.getConsulta().getDetalleConsulta().forEach( detalle -> {
            detalle.setConsulta(dto.getConsulta());
        });

        repo.save(dto.getConsulta());

        dto.getListaExamen().forEach(examen -> {
                             //registrar(idConsulta, idExamen)
            consultaExamenRepo.registrar(dto.getConsulta().getIdConsulta(), examen.getIdExamen());
        });

        return dto.getConsulta();
    }



//    @Override
//    public Consulta registrar(Consulta obj) {
//        obj.getDetalleConsulta().forEach( detalleConsulta -> {
//            detalleConsulta.setConsulta(obj);
//        });
//        return repo.save(obj);
//    }
//
//    @Override
//    public Consulta modificar(Consulta obj) {
//        return repo.save(obj);
//    }
//
//    @Override
//    public List<Consulta> listar() {
//        return repo.findAll();
//    }
//
//    @Override
//    public Consulta listarPorId(Integer id) {
//        Optional<Consulta> op = repo.findById(id);
//        return op.isPresent() ? op.get() : new Consulta();
//    }
//
//    @Override
//    public boolean eliminar(Integer id) {
//        repo.deleteById(id);
//        return true;
//    }
}
