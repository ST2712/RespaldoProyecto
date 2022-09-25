package com.co.software.empresas.desaInt.repository;

import com.co.software.empresas.desaInt.domain.EntityEmpresa;
import com.co.software.empresas.desaInt.domain.EntityMovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositoryMovimientoDinero extends JpaRepository<EntityMovimientoDinero, Long> {

    @Query("SELECT d from EntityMovimientoDinero d WHERE d.conceptoMovimiento LIKE %?1%" +
            "OR d.fecha LIKE %?1% OR d.empleado.nombre LIKE %?1%")
    List<EntityMovimientoDinero> findAll(String palabraClave);
}
