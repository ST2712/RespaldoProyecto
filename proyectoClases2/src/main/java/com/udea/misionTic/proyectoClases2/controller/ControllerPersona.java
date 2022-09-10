package com.udea.misionTic.proyectoClases2.controller;

import com.udea.misionTic.proyectoClases2.domain.Persona;
import com.udea.misionTic.proyectoClases2.repository.EntityPersona;
import com.udea.misionTic.proyectoClases2.services.ServicePersona;
import com.udea.misionTic.proyectoClases2.util.EnumRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/persona")
public class ControllerPersona {

    @Autowired
    ServicePersona servicePersona;

    @GetMapping(path = "/udea/mintic/program", produces = "application/json")
    public ResponseEntity<String> callServicePrograma(){

        Persona alumno = new Persona();
        alumno.setNombre("Santiago");
        alumno.setApellido("Trujillo");
        alumno.setEdad(18);

        String salida = servicePersona.inscribirAlumno(alumno);

        return new ResponseEntity<String>(salida, HttpStatus.NOT_FOUND);
    }

    @GetMapping (path = "/udea/mintic/dowhile", produces = "application/json")
    public ArrayList doWhileController(){

        ArrayList<String> salida = new ArrayList<>();
        salida = servicePersona.doWhile(7);

        return salida;
    }

    @GetMapping (path ="/udea/mintic/listaPersonas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Persona> listaPersonas(){

        return servicePersona.listar();
    }

    @PostMapping(path = "/udea/mintic/crearPersona", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona){

        boolean salida = servicePersona.addPersona(persona);

        if(salida == true){
            return new ResponseEntity<Persona>(persona, HttpStatus.OK);
        }
        else{
            return new ResponseEntity("Error de ejecucion",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping (path ="/udea/mintic/buscarPersona/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> buscarPersona(@PathVariable int id){

        Persona p = servicePersona.buscarPersona(id);

        if(p != null){
            return new ResponseEntity<Persona>(p, HttpStatus.OK);
        }
        else{
            return new ResponseEntity("Error de ejecucion",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping (path = "/udea/mintic/crearPersona/{doc}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> crearPersonaCondicional(@RequestBody Persona persona, @PathVariable String doc){

        switch (doc){
            case "CC":
                servicePersona.addPersonaCC(persona, doc);
                break;
            case "TI":
                servicePersona.addPersonaTI(persona, doc);
                break;
            default:
                return new ResponseEntity("Error de ejecucion",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }

    @PutMapping(path = "/udea/mintic/actualizarPersona", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Persona> actualizarPersona(@RequestParam int id, @RequestParam String nombreModificado){

        Persona p = servicePersona.buscarPersona(id);

        p.setNombre(nombreModificado);
        System.out.println("Metodo put");
        return new ResponseEntity<Persona>(p,HttpStatus.OK);

    }

    @PatchMapping(path = "/udea/mintic/actualizarPP", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> actualizarPersonaParcial(){

        String retorno = "Actualizacion parcial de dominio Persona";
        System.out.println("Metodo patch");
        return new ResponseEntity<String >(retorno,HttpStatus.OK);
    }

    @DeleteMapping(path = "/udea/mintic/borrarPersona/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> borrarPersona(@PathVariable int id){

        Persona p = servicePersona.buscarPersona(id);

        Boolean salida = servicePersona.borrarPersona(p);

        return new ResponseEntity<Boolean>(salida, HttpStatus.OK);
    }

    @GetMapping(path = "/udea/mintic/listarTodosJPA",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listarTodo(){

        return new ResponseEntity<Object>(servicePersona.listarTodosJPA(), HttpStatus.OK);
    }

    @PostMapping(path = "/udea/mintic/insertarPersonaJPA", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> insertarPersona(@RequestBody EntityPersona persona){

        return new ResponseEntity<Boolean>(servicePersona.insertarPersonaJPA(persona), HttpStatus.OK);
    }

    //Hay que validar si existe, implementar si hay tiempo
    @PutMapping(path = "/udea/mintic/actualizarTodoJPA", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> actualizarTodoJPA(@RequestBody EntityPersona persona){

        return new ResponseEntity<Boolean>(servicePersona.actualizarTodoJPA(persona), HttpStatus.OK);
    }

    @PatchMapping(path = "/udea/mintic/actualizarParcialJPA", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insertarParcialJPA(@RequestBody EntityPersona persona){

        servicePersona.actualizarParcialJPA(persona);
    }

    @DeleteMapping(path = "/udea/mintic/borrarPersonaJPA/{id}")
    public void borrarPersonaJPA(@PathVariable Long id){

        servicePersona.borrarPersonaJPA(id);
    }

    @PostMapping(path = "/udea/mintic/insertarPersonaRol", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insertarPersonaRol(@RequestBody EntityPersona persona){

        servicePersona.insertarPersonaRol(persona);
    }


}
