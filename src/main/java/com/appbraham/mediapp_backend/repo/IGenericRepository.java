package com.appbraham.mediapp_backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

//No es una entidad de la BD, se deshabilita ese comportamiento con @NoRepositoryBean
@NoRepositoryBean
public interface IGenericRepository<T, ID> extends JpaRepository<T, ID> {

}
