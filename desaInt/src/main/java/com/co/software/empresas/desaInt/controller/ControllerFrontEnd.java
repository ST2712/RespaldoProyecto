package com.co.software.empresas.desaInt.controller;

import com.co.software.empresas.desaInt.domain.EntityEmpleado;
import com.co.software.empresas.desaInt.domain.EntityEmpresa;
import com.co.software.empresas.desaInt.domain.EntityMovimientoDinero;
import com.co.software.empresas.desaInt.repository.RepositoryEmpleado;
import com.co.software.empresas.desaInt.services.ServiceEmpleado;
import com.co.software.empresas.desaInt.services.ServiceEmpresa;
import com.co.software.empresas.desaInt.services.ServiceMovimientoDinero;
import com.co.software.empresas.desaInt.util.EnumRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ControllerFrontEnd {

    @Autowired
    ServiceEmpleado serviceEmpleado;
    @Autowired
    ServiceEmpresa serviceEmpresa;
    @Autowired
    ServiceMovimientoDinero serviceMovimientoDinero;

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
    public String dashboard(Model modelo, @AuthenticationPrincipal OidcUser principal){

        if(principal != null) {
            modelo.addAttribute("nombreUsuario", principal.getIdToken().getClaims().get("nickname"));
            return "dashboard";
        }
        else{
            return "index";
        }
    }

    @GetMapping(path = "/dashboardCrearEmpleado")
    public String dashboardCrearEmpleado(Model modelo, @AuthenticationPrincipal OidcUser principal){

        if(principal != null) {
            modelo.addAttribute("nombreUsuario", principal.getIdToken().getClaims().get("nickname"));
            return "dashboardCrearEmpleado";
        }
        else{
            return "index";
        }
    }

    @GetMapping(path = "/listarEmpresas")
    public String listarEmpresas(Model modelo, @AuthenticationPrincipal OidcUser principal){

        if(principal != null){
            List<EntityEmpresa> listEmpresas = serviceEmpresa.listarEmpresasJpa();
            modelo.addAttribute("empresas", listEmpresas);
            modelo.addAttribute("nombreUsuario", principal.getIdToken().getClaims().get("nickname"));

            return "listarEmpresas";
        }
        else{
            return "index";
        }
    }

    @GetMapping(path = "/dashboardListarEmpresas")
    public String dashboardListarEmpresas(Model modelo, @AuthenticationPrincipal OidcUser principal){

        if(principal != null){
            List<EntityEmpresa> listEmpresas = serviceEmpresa.listarEmpresasJpa();
            modelo.addAttribute("empresas", listEmpresas);
            modelo.addAttribute("nombreUsuario", principal.getIdToken().getClaims().get("nickname"));
            return "dashboardListarEmpresas";
        }
        else{
            return "index";
        }
    }

    @GetMapping(path = "/editarEmpresa/{idEmpresa}")
    public String editarEmpresa(Model modelo, @PathVariable("idEmpresa") Long idEmpresa){

        EntityEmpresa eTemp = serviceEmpresa.buscarEmpresaPorIdJpa(idEmpresa);
        modelo.addAttribute("eEmpresa", eTemp);
        return "editarEmpresa";
    }

    @GetMapping(path = "/crearEmpleado/{idEmpresa}")
    public String crearEmpleado(Model modelo, @PathVariable("idEmpresa") Long idEmpresa){
        modelo.addAttribute("nEmpleado", new EntityEmpleado(serviceEmpresa.buscarEmpresaPorIdJpa(idEmpresa)));
        return "crearEmpleado";
    }

    @GetMapping(path = "/dashboardListarEmpleados")
    public String dashboardListarEmpleados(Model modelo, @AuthenticationPrincipal OidcUser principal){

        if(principal != null){
            List<EntityEmpresa> listEmpresas = serviceEmpresa.listarEmpresasJpa();
            modelo.addAttribute("empresas", listEmpresas);
            modelo.addAttribute("nombreUsuario", principal.getIdToken().getClaims().get("nickname"));
            return "dashboardListarEmpleados";
        }
        else{
            return "index";
        }
    }

    @GetMapping(path = "/crearEmpresa")
    public String crearEmpresa(Model modelo){
        modelo.addAttribute("nEmpresa", new EntityEmpresa());
        return "crearEmpresa";
    }

    @GetMapping(path = "/dashboardCrearEmpresa")
    public String dashboardCrearEmpresa(Model modelo, @AuthenticationPrincipal OidcUser principal){

        if(principal != null) {
            modelo.addAttribute("nombreUsuario", principal.getIdToken().getClaims().get("nickname"));
            return "dashboardCrearEmpresa";
        }
        else{
            return "index";
        }
    }

    @GetMapping(path = "/listarMovDinero")
    public String listarMovDinero(Model modelo, @AuthenticationPrincipal OidcUser principal){

        List<EntityMovimientoDinero> listMovDinero = serviceMovimientoDinero.listarMovDineroJpa();
        modelo.addAttribute("movimientos", listMovDinero);
        return "listarMovDinero";
    }

    @GetMapping(path = "/dashboardListarMovDinero")
    public String dashboardListarMovDinero(Model modelo, @AuthenticationPrincipal OidcUser principal){

        if(principal != null) {
            modelo.addAttribute("nombreUsuario", principal.getIdToken().getClaims().get("nickname"));
            return "dashboardListarMovDinero";
        }
        else{
            return "index";
        }
    }

}
