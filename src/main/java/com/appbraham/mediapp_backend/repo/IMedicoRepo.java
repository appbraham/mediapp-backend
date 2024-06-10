package com.appbraham.mediapp_backend.repo;

import com.appbraham.mediapp_backend.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository, En este caso es opcional
public interface IMedicoRepo extends JpaRepository<Medico, Integer> {

}
