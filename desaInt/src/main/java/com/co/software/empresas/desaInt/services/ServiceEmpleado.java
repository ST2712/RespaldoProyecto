package com.co.software.empresas.desaInt.services;

import com.co.software.empresas.desaInt.domain.EntityEmpleado;
import com.co.software.empresas.desaInt.domain.EntityEmpresa;
import com.co.software.empresas.desaInt.domain.EntityMovimientoDinero;
import com.co.software.empresas.desaInt.repository.RepositoryEmpleado;
import com.co.software.empresas.desaInt.repository.RepositoryEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEmpleado {

    @Autowired
    RepositoryEmpleado repositoryEmpleado;

    @Autowired
    RepositoryEmpresa repositoryEmpresa;

    public Boolean insertarEmpleadoJpa(EntityEmpleado empleado){

        try{
            repositoryEmpleado.save(empleado);
        } catch (Exception e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public List<EntityEmpleado> listarEmpleadosJpa(){

        List<EntityEmpleado> list =repositoryEmpleado.findAll();
        return list;
    }

    public Boolean borrarEmpleadoJpa(Long id){

        List<EntityEmpleado> list = listarEmpleadosJpa();
        Boolean encontrado = Boolean.FALSE;

        for (int i = 0; i < list.size() && !encontrado; i++){

            EntityEmpleado empleadoActual = list.get(i);
            if( empleadoActual.getId() == id){
                repositoryEmpleado.delete(empleadoActual);
                encontrado = Boolean.TRUE;
            }
        }
        return encontrado;
    }

    public EntityEmpleado buscarEmpleadoPorIdJpa(Long id){

        return repositoryEmpleado.findById(id).orElse(null);
    }

    public Boolean actualizarDatosEmpleadoJpa(EntityEmpleado empleado, Long idEmpresa){

        EntityEmpleado empTemp = buscarEmpleadoPorIdJpa(empleado.getId());

        if(empTemp == null) {
            return Boolean.FALSE;
        }

        if(empleado.getNombre() != null){
            empTemp.setNombre(empleado.getNombre());
        }
        if(empleado.getCorreo() != null){
            empTemp.setCorreo(empleado.getCorreo());
        }
        if (idEmpresa != null){
            empTemp.setEmpresa(repositoryEmpresa.findById(idEmpresa).orElse(null));
        }
        if(empleado.getRol() != null){
            empTemp.setRol(empleado.getRol());
        }
        if(empleado.getMovimientoDineroCollection() != null){
            empTemp.setMovimientoDineroCollection(empleado.getMovimientoDineroCollection());
        }

        repositoryEmpleado.save(empTemp);

        return Boolean.TRUE;
    }

    public Boolean asginarEmpleadoConEmpresaJpa(EntityEmpleado empleado, EntityEmpresa empresa){

        try{
            EntityEmpleado entityEmpleadoTemp = new EntityEmpleado(empleado.getNombre(), empleado.getCorreo(), empleado.getRol(), empresa);
            repositoryEmpleado.save(entityEmpleadoTemp);
        } catch (Exception e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
