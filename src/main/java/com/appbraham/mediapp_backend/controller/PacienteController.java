package com.appbraham.mediapp_backend.controller;

import com.appbraham.mediapp_backend.exception.ModeloNotFoundException;
import com.appbraham.mediapp_backend.model.Paciente;
import com.appbraham.mediapp_backend.service.IPacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService service;

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        List<Paciente> lista = service.listar();
        return new ResponseEntity<List<Paciente>>( lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> listarPorId(@PathVariable("id") Integer id){
        Paciente paciente = service.listarPorId(id);

        if(paciente.getIdPaciente() == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
        }

        return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
    }

    //Usando HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<Paciente> ListarPorIdHateoas(@PathVariable("id") Integer id){

        Paciente paciente = service.listarPorId(id);
        //localhost:8080/paciente/{id}
        EntityModel<Paciente> recurso = EntityModel.of(paciente);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
        recurso.add(linkTo.withRel("pacienteRecurso"));

        return recurso;
    }

    //@Valid, valida según las validaciones de nuestro modelo Paciente
    //@RequestBody serializa el JSON a un obtejo de tipo Java
//    @PostMapping
//    public ResponseEntity<Paciente> registrar(@Valid @RequestBody Paciente paciente){
//        Paciente nuevoPaciente = service.registrar(paciente);
//        return new ResponseEntity<Paciente>(nuevoPaciente, HttpStatus.CREATED);
//    }

    //API Nivel 3 - Richardson
    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Paciente paciente){
        Paciente nuevoPaciente = service.registrar(paciente);
        //localhost:8080/paciente/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nuevoPaciente.getIdPaciente()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Paciente> modificar(@Valid @RequestBody Paciente paciente){
        Paciente nuevoPaciente = service.modificar(paciente);
        return new ResponseEntity<Paciente>(nuevoPaciente, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
        Paciente p = service.listarPorId(id);
        if (p.getIdPaciente() == null){
            throw new ModeloNotFoundException("El Paciente no existe, ID: " +id);
        }
        service.eliminar(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
