package com.practicas.Practicas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGeneralRepository <T,ID> extends CrudRepository<T,ID> {
}
