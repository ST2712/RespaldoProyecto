package com.co.software.empresas.desaInt.repository;

import com.co.software.empresas.desaInt.domain.EntityEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryEmpresa extends JpaRepository<EntityEmpresa, Long> {
}
