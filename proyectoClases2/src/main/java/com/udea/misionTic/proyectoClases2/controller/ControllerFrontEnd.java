package com.udea.misionTic.proyectoClases2.controller;

import com.udea.misionTic.proyectoClases2.repository.EntityPersona;
import com.udea.misionTic.proyectoClases2.services.ServicePersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ControllerFrontEnd {

    @Autowired
    ServicePersona servicePersona;

    @GetMapping(path = "/")
    public String home(){

        return "index";
    }

    @GetMapping(path = "/pagina2")
    public String pagina2(Model modelo){

        List<EntityPersona> listPersonas = servicePersona.listarTodosJPA();
        modelo.addAttribute("personas", listPersonas);

        return "pagina2";
    }

    @GetMapping(path = "/crearPersona")
    public String crearPersona(Model modelo){

        modelo.addAttribute("nPersona", new EntityPersona());
        return "crearPersona";
    }

    @GetMapping(path = "/editarPersona/{id}")
    public String editarPersona(Model modelo, @PathVariable("id") Long id){

        EntityPersona pTemp = servicePersona.buscarPersonaId(id);
        modelo.addAttribute("ePersona", pTemp);
        return "editarPersona";
    }
}
