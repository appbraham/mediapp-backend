package com.appbraham.mediapp_backend.repo;

import com.appbraham.mediapp_backend.model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository, En este caso es opcional
public interface IEspecialidadRepo extends IGenericRepository<Especialidad, Integer> {

}
