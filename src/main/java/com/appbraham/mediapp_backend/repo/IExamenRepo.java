package com.appbraham.mediapp_backend.repo;

import com.appbraham.mediapp_backend.model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository, En este caso es opcional
public interface IExamenRepo extends JpaRepository<Examen, Integer> {

}
