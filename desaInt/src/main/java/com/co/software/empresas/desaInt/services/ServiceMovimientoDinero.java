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
            EntityMovimientoDinero entityMovDineroTemp = new EntityMovimientoDinero(movimientoDinero.getMontoMovimiento(), movimientoDinero.getConceptoMovimiento(), empleado);
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

    public List<EntityMovimientoDinero> listarMovDineroBusqueda(String palabraClave){

        if(palabraClave != null){
            return repositoryMovimientoDinero.findAll(palabraClave);
        }
        return repositoryMovimientoDinero.findAll();
    }

    public Double calcularTotal(List<EntityMovimientoDinero> listTotal){

        Double total = 0.0;
        for (int i = 0; i < listTotal.size(); i++){

            total += listTotal.get(i).getMontoMovimiento();
        }
        return total;
    }

    public Boolean borrarMovDineroJpa(Long id){

        EntityMovimientoDinero movDineroBorrar = buscarMovimientoDineroIdJPA(id);

        if(movDineroBorrar == null){
            return Boolean.FALSE;
        }
        else{
            repositoryMovimientoDinero.delete(movDineroBorrar);
            return Boolean.TRUE;
        }
    }

    public EntityMovimientoDinero buscarMovimientoDineroIdJPA(Long id){

        return repositoryMovimientoDinero.findById(id).orElse(null);
    }

    public Boolean actualizarDatosMovDineroJpa(EntityMovimientoDinero movimientoDinero, EntityEmpleado empleado){

        EntityMovimientoDinero movDineroTemp = repositoryMovimientoDinero.findById(movimientoDinero.getId()).orElse(null);

        if(movDineroTemp == null){
            return Boolean.FALSE;
        }

        if(movimientoDinero.getMontoMovimiento() != null){
            movDineroTemp.setMontoMovimiento(movimientoDinero.getMontoMovimiento());
        }
        if(movimientoDinero.getConceptoMovimiento() != null){
            movDineroTemp.setConceptoMovimiento(movimientoDinero.getConceptoMovimiento());
        }
        if(empleado != null){
            movDineroTemp.setEmpleado(empleado);
        }

        repositoryMovimientoDinero.save(movDineroTemp);

        return Boolean.TRUE;
    }
}
