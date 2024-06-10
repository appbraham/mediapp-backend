package com.appbraham.mediapp_backend.controller;

import com.appbraham.mediapp_backend.exception.ModeloNotFoundException;
import com.appbraham.mediapp_backend.model.Examen;
import com.appbraham.mediapp_backend.service.IExamenService;
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
@RequestMapping("/examenes")
public class ExamenController {

    @Autowired
    private IExamenService service;

    @GetMapping
    public ResponseEntity<List<Examen>> listar() {
        List<Examen> lista = service.listar();
        return new ResponseEntity<List<Examen>>( lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Examen> listarPorId(@PathVariable("id") Integer id){
        Examen examen = service.listarPorId(id);

        if(examen.getIdExamen() == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
        }

        return new ResponseEntity<Examen>(examen, HttpStatus.OK);
    }

    //Usando HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<Examen> ListarPorIdHateoas(@PathVariable("id") Integer id){

        Examen examen = service.listarPorId(id);
        //localhost:8080/examen/{id}
        EntityModel<Examen> recurso = EntityModel.of(examen);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
        recurso.add(linkTo.withRel("examenRecurso"));

        return recurso;
    }

    //@Valid, valida según las validaciones de nuestro modelo Examen
    //@RequestBody serializa el JSON a un obtejo de tipo Java
//    @PostMapping
//    public ResponseEntity<Examen> registrar(@Valid @RequestBody Examen examen){
//        Examen nuevoExamen = service.registrar(examen);
//        return new ResponseEntity<Examen>(nuevoExamen, HttpStatus.CREATED);
//    }

    //API Nivel 3 - Richardson
    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Examen examen){
        Examen nuevoExamen = service.registrar(examen);
        //localhost:8080/examen/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nuevoExamen.getIdExamen()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Examen> modificar(@Valid @RequestBody Examen examen){
        Examen nuevoExamen = service.modificar(examen);
        return new ResponseEntity<Examen>(nuevoExamen, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
        Examen m = service.listarPorId(id);
        if (m.getIdExamen() == null){
            throw new ModeloNotFoundException("El Examen no existe, ID: " +id);
        }
        service.eliminar(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
