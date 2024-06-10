package com.appbraham.mediapp_backend.repo;

import com.appbraham.mediapp_backend.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository, En este caso es opcional
public interface IPacienteRepo extends JpaRepository<Paciente, Integer> {

}
