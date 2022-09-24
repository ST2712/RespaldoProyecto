package com.co.software.empresas.desaInt.controller;

import com.co.software.empresas.desaInt.domain.EntityMovimientoDinero;
import com.co.software.empresas.desaInt.services.ServiceEmpleado;
import com.co.software.empresas.desaInt.services.ServiceMovimientoDinero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping(path = "/buscarMovimientoDineroPorIdJpa/{id}")
    public ResponseEntity<EntityMovimientoDinero> buscarMovimientoDineroPorIdJpa(@PathVariable Long id){

        return new ResponseEntity<EntityMovimientoDinero>(serviceMovimientoDinero.buscarMovimientoDineroIdJPA(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/borrarMovimientoDineroJpa/{id}")
    public ResponseEntity<Boolean> borrarMovimientoDineroJpa(@PathVariable Long id){

        return new ResponseEntity<Boolean>(serviceMovimientoDinero.borrarMovDineroJpa(id), HttpStatus.OK);
    }

    @PatchMapping(path = "/actualizarMovimientoDineroParcialJpa/{idEmpleado}")
    public ResponseEntity<Boolean> actualizarMovimientoDineroParcialJpa(@RequestBody EntityMovimientoDinero movDinero, @PathVariable Long idEmpleado){

        return new ResponseEntity<Boolean>(serviceMovimientoDinero.actualizarDatosMovDineroJpa(movDinero, serviceEmpleado.buscarEmpleadoPorIdJpa(idEmpleado)), HttpStatus.OK);
    }

    //Nuevos metodos para aplicar al FrontEnd
    //----------------------------------------------------------------------------------------------------------------------------------------

    @PostMapping(path = "/insertarMovDinero")
    public RedirectView insertarMovDinero(@ModelAttribute EntityMovimientoDinero movimientoDinero, Model modelo){

        modelo.addAttribute(movimientoDinero);
        Boolean condicion = serviceMovimientoDinero.insertarMovDineroJpa(movimientoDinero, movimientoDinero.getEmpleado());

        if(condicion.equals(Boolean.TRUE)){
            return new RedirectView("/pagina2");
        }
        else{
            return new RedirectView("/error");
        }
    }

    @DeleteMapping(path = "/borrarMovDinero/{idMovDinero}")
    public RedirectView borrarMovDinero(@PathVariable Long idMovDinero){

        Boolean elimino = serviceMovimientoDinero.borrarMovDineroJpa(idMovDinero);

        if(elimino.equals(Boolean.TRUE)){
            return new RedirectView("/listarMovDinero");
        }
        else{
            return new RedirectView("/error");
        }
    }

    @PatchMapping(path = "/editarMovDinero")
    public RedirectView editarMovDinero(@ModelAttribute EntityMovimientoDinero movDinero, Model modelo){

        modelo.addAttribute(movDinero);
        if(serviceMovimientoDinero.actualizarDatosMovDineroJpa(movDinero, movDinero.getEmpleado()).equals(Boolean.TRUE)){
            return new RedirectView("/listarMovDinero");
        }
        else{
            return new RedirectView("/error");
        }
    }

}
