package com.co.software.empresas.desaInt.controller;

import com.co.software.empresas.desaInt.domain.EntityMovimientoDinero;
import com.co.software.empresas.desaInt.services.ServiceEmpleado;
import com.co.software.empresas.desaInt.services.ServiceMovimientoDinero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movDinero")
public class ControllerMovimientoDinero {

    @Autowired
    ServiceMovimientoDinero serviceMovimientoDinero;

    @Autowired
    ServiceEmpleado serviceEmpleado;

    @PostMapping(path = "/insertarMovimientoDineroJpa/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> insertarMovimientoDineroJpa(@RequestBody EntityMovimientoDinero entityMovimientoDinero, @PathVariable Long id){

        return new ResponseEntity<Boolean>(serviceMovimientoDinero.insertarMovDineroJpa(entityMovimientoDinero, serviceEmpleado.buscarEmpleadoPorIdJpa(id)), HttpStatus.OK);
    }

    @GetMapping(path = "/listarMovDineroJpa", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listarMovDineroJpa(){

        return new ResponseEntity<Object>(serviceMovimientoDinero.listarMovDineroJpa(), HttpStatus.OK);
    }

}
