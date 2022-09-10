package com.co.software.empresas.desaInt.repository;

import com.co.software.empresas.desaInt.domain.EntityMovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryMovimientoDinero extends JpaRepository<EntityMovimientoDinero, Long> {
}
