package com.co.software.empresas.desaInt.controller;

import com.co.software.empresas.desaInt.domain.EntityEmpleado;
import com.co.software.empresas.desaInt.services.ServiceEmpleado;
import com.co.software.empresas.desaInt.services.ServiceEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/empleado")
public class ControllerEmpleado {

    @Autowired
    ServiceEmpleado serviceEmpleado;

    @Autowired
    ServiceEmpresa serviceEmpresa;

    @PostMapping(path = "/insertarEmpleadoJPA", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> insertarEmpleadoJpa(@RequestBody EntityEmpleado empleado){

        return new ResponseEntity<Boolean>(serviceEmpleado.insertarEmpleadoJpa(empleado), HttpStatus.OK);
    }

    @GetMapping(path = "/listarEmpleadosJpa", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listarEmpleadosJpa(){

        return new ResponseEntity<Object>(serviceEmpleado.listarEmpleadosJpa(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/borrarEmpleadoJpa/{id}")
    public ResponseEntity<Boolean> borrarEmpleadoJpa(@PathVariable Long id){

        return new ResponseEntity<Boolean>(serviceEmpleado.borrarEmpleadoJpa(id), HttpStatus.OK);
    }

    @GetMapping(path = "/buscarEmpleadoIdJpa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityEmpleado> buscarEmpleadoPorIdJpa(@PathVariable Long id){

        return new ResponseEntity<EntityEmpleado>(serviceEmpleado.buscarEmpleadoPorIdJpa(id), HttpStatus.OK);
    }

    @PatchMapping(path = "/actualizarEmpleadoParcialJpa/{idEmpresa}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> actualizarEmpleadoParcialJpa(@RequestBody EntityEmpleado empleado, @PathVariable Long idEmpresa){

        return new ResponseEntity<Boolean>(serviceEmpleado.actualizarDatosEmpleadoJpa(empleado, idEmpresa), HttpStatus.OK);
    }

    @PostMapping(path = "/insertarEmpleadoConEmpresaJpa/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> insertarEmpleadoConEmpresaJpa(@RequestBody EntityEmpleado empleado, @PathVariable Long id){

        Boolean condicion = serviceEmpleado.asginarEmpleadoConEmpresaJpa(empleado, serviceEmpresa.buscarEmpresaPorIdJpa(id));
        return new ResponseEntity<Boolean>(condicion, HttpStatus.OK);
    }

}
