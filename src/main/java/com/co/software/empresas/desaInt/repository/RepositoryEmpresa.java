package com.co.software.empresas.desaInt.repository;

import com.co.software.empresas.desaInt.domain.EntityEmpleado;
import com.co.software.empresas.desaInt.domain.EntityEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositoryEmpresa extends JpaRepository<EntityEmpresa, Long> {

    @Query("SELECT e from EntityEmpresa e WHERE e.nombre LIKE %?1%" +
            "OR e.direccion LIKE %?1%")
    List<EntityEmpresa> findAll(String palabraClave);
}
