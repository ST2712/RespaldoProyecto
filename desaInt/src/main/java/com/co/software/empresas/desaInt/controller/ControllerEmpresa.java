package com.co.software.empresas.desaInt.controller;

import com.co.software.empresas.desaInt.domain.EntityEmpresa;
import com.co.software.empresas.desaInt.services.ServiceEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/empresa")
public class ControllerEmpresa {

    @Autowired
    ServiceEmpresa serviceEmpresa;

    @PostMapping(path = "/insertarEmpresaJpa", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> insertarEmpresaJpa(@RequestBody EntityEmpresa empresa){

        return new ResponseEntity<Boolean>(serviceEmpresa.insertarEmpresaJpa(empresa), HttpStatus.OK);
    }

    @GetMapping(path = "/listarEmpresasJpa", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listarEmpresasJpa(){

        return new ResponseEntity<Object>(serviceEmpresa.listarEmpresasJpa(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/borrarEmpresaJpa/{id}")
    public ResponseEntity<Boolean> borrarEmpresaJpa(@PathVariable Long id){

        Boolean condicion = serviceEmpresa.borrarEmpresaJpa(id);
        if(condicion == Boolean.TRUE){
            return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/buscarEmpresaPorIdJpa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityEmpresa> buscarEmpresaPorIdJpa(@PathVariable Long id){

        return new ResponseEntity<EntityEmpresa>(serviceEmpresa.buscarEmpresaPorIdJpa(id), HttpStatus.OK);
    }

    @PatchMapping(path = "/actualizarDatosEmpresaJpa", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> actualizarDatosEmpresaJpa(@RequestBody EntityEmpresa empresa){

        return new ResponseEntity<Boolean>(serviceEmpresa.actualizarDatosEmpresaJpa(empresa), HttpStatus.OK);
    }
}
