package com.co.software.empresas.desaInt.repository;

import com.co.software.empresas.desaInt.domain.EntityEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositoryEmpleado extends JpaRepository<EntityEmpleado, Long> {

    @Query("SELECT e from EntityEmpleado e WHERE e.nombre LIKE %?1%" +
            "OR e.correo LIKE %?1%" +
            "OR e.empresa.nombre LIKE %?1% ")
    List<EntityEmpleado> findAll(String palabraClave);
}
