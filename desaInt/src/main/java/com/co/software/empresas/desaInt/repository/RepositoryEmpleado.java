package com.co.software.empresas.desaInt.repository;

import com.co.software.empresas.desaInt.domain.EntityEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryEmpleado extends JpaRepository<EntityEmpleado, Long> {
}
