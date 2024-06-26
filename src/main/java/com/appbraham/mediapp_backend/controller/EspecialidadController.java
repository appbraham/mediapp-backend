package com.appbraham.mediapp_backend.controller;

import com.appbraham.mediapp_backend.exception.ModeloNotFoundException;
import com.appbraham.mediapp_backend.model.Especialidad;
import com.appbraham.mediapp_backend.service.IEspecialidadService;
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
@RequestMapping("/especialidades")
public class EspecialidadController {

    @Autowired
    private IEspecialidadService service;

    @GetMapping
    public ResponseEntity<List<Especialidad>> listar() throws Exception {
        List<Especialidad> lista = service.listar();
        return new ResponseEntity<List<Especialidad>>( lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Especialidad especialidad = service.listarPorId(id);

        if(especialidad == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
        }

        return new ResponseEntity<Especialidad>(especialidad, HttpStatus.OK);
    }

    //Usando HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<Especialidad> ListarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {

        Especialidad especialidad = service.listarPorId(id);
        //localhost:8080/especialidad/{id}
        EntityModel<Especialidad> recurso = EntityModel.of(especialidad);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
        recurso.add(linkTo.withRel("especialidadRecurso"));

        return recurso;
    }

    //@Valid, valida según las validaciones de nuestro modelo Especialidad
    //@RequestBody serializa el JSON a un obtejo de tipo Java
//    @PostMapping
//    public ResponseEntity<Especialidad> registrar(@Valid @RequestBody Especialidad especialidad){
//        Especialidad nuevoEspecialidad = service.registrar(especialidad);
//        return new ResponseEntity<Especialidad>(nuevoEspecialidad, HttpStatus.CREATED);
//    }

    //API Nivel 3 - Richardson
    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Especialidad especialidad) throws Exception {
        Especialidad nuevaEspecialidad = service.registrar(especialidad);
        //localhost:8080/especialidad/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nuevaEspecialidad.getIdEspecialidad()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Especialidad> modificar(@Valid @RequestBody Especialidad especialidad) throws Exception {
        Especialidad nuevaEspecialidad = service.modificar(especialidad);
        return new ResponseEntity<Especialidad>(nuevaEspecialidad, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) throws Exception {
        Especialidad m = service.listarPorId(id);
        if (m == null){
            throw new ModeloNotFoundException("La Especialidad no existe, ID: " +id);
        }
        service.eliminar(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
