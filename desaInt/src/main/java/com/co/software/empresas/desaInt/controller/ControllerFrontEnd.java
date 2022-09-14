package com.co.software.empresas.desaInt.controller;

import com.co.software.empresas.desaInt.domain.EntityEmpleado;
import com.co.software.empresas.desaInt.services.ServiceEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ControllerFrontEnd {

    @Autowired
    ServiceEmpleado serviceEmpleado;

    @GetMapping(path = "/")
    public String home(){

        return "index";
    }

    @GetMapping(path = "/pagina2")
    public String pagina2(Model modelo){

        List<EntityEmpleado> listEmpleados = serviceEmpleado.listarEmpleadosJpa();
        modelo.addAttribute("empleados", listEmpleados);

        return "pagina2";
    }

    @GetMapping(path = "/editarEmpleado/{idEmpleado}")
    public String editarPersona(Model modelo, @PathVariable("idEmpleado") Long idEmpleado){

        EntityEmpleado eTemp = serviceEmpleado.buscarEmpleadoPorIdJpa(idEmpleado);
        modelo.addAttribute("eEmpleado", eTemp);
        return "editarEmpleado";
    }
}
