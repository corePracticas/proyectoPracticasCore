package com.practicas.Practicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGeneralRepository <T,ID> extends JpaRepository<T,ID> {
}
