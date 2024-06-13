package com.appbraham.mediapp_backend.repo;

import com.appbraham.mediapp_backend.model.ConsultaExamen;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IConsultaExamenRepo extends JpaRepository<ConsultaExamen, Integer> {

//    @Transactional
    @Modifying //Permite hacer sentencias de modificación create, update y delete. Permite usar DML Data Manipulation Language
    @Query(value = "INSERT INTO consulta_examen(id_consulta, id_examen) VALUES (:idConsulta, :idExamen)", nativeQuery = true)
    Integer registrar(@Param("idConsulta") Integer idConsulta, @Param("idExamen") Integer idExamen);
}
