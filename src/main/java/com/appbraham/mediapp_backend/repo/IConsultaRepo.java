package com.appbraham.mediapp_backend.repo;

import com.appbraham.mediapp_backend.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository, En este caso es opcional
public interface IConsultaRepo extends IGenericRepository<Consulta, Integer> {

}
