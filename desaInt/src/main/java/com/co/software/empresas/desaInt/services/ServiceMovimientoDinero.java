package com.co.software.empresas.desaInt.services;

import com.co.software.empresas.desaInt.domain.EntityEmpleado;
import com.co.software.empresas.desaInt.domain.EntityMovimientoDinero;
import com.co.software.empresas.desaInt.repository.RepositoryMovimientoDinero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceMovimientoDinero {

    @Autowired
    RepositoryMovimientoDinero repositoryMovimientoDinero;

    @Autowired
    ServiceEmpleado serviceEmpleado;

    public Boolean insertarMovDineroJpa(EntityMovimientoDinero movimientoDinero, EntityEmpleado empleado){

        try{
            EntityMovimientoDinero entityMovDineroTemp = new EntityMovimientoDinero(movimientoDinero.getMontoMoviento(), movimientoDinero.getConceptoMovimiento(), empleado);
            repositoryMovimientoDinero.save(entityMovDineroTemp);
        } catch (Exception e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public List<EntityMovimientoDinero> listarMovDineroJpa(){

        List<EntityMovimientoDinero> list = repositoryMovimientoDinero.findAll();
        return list;
    }

    public Boolean borrarMovDineroJpa(Long id){

        List<EntityMovimientoDinero> list = listarMovDineroJpa();
        Boolean encontrado = Boolean.FALSE;

        for (int i = 0; i < list.size() && !encontrado; i++){

            EntityMovimientoDinero movActual = list.get(i);
            if( movActual.getId() == id){
                repositoryMovimientoDinero.delete(movActual);
                encontrado = Boolean.TRUE;
            }
        }
        return encontrado;
    }
}
