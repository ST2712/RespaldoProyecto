package com.co.software.empresas.desaInt.controller;

import com.co.software.empresas.desaInt.domain.EntityEmpleado;
import com.co.software.empresas.desaInt.repository.RepositoryEmpleado;
import com.co.software.empresas.desaInt.services.ServiceEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
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
    public String home(Model model, @AuthenticationPrincipal OidcUser principal){
        return "index";
    }

    @GetMapping(path = "/pagina2")
    public String pagina2(Model modelo, @AuthenticationPrincipal OidcUser principal){

        if(principal != null){
            List<EntityEmpleado> listEmpleados = serviceEmpleado.listarEmpleadosJpa();
            modelo.addAttribute("empleados", listEmpleados);
            modelo.addAttribute("nombreUsuario", principal.getIdToken().getClaims().get("nickname"));

            return "pagina2";
        }
        else{
            return "index";
        }
    }

    @GetMapping(path = "/editarEmpleado/{idEmpleado}")
    public String editarPersona(Model modelo, @PathVariable("idEmpleado") Long idEmpleado){

        EntityEmpleado eTemp = serviceEmpleado.buscarEmpleadoPorIdJpa(idEmpleado);
        modelo.addAttribute("eEmpleado", eTemp);
        return "editarEmpleado";
    }

    @GetMapping(path = "/dashboard")
    public String dashboard(){
        return "dashboard";
    }

}
