package com.appbraham.mediapp_backend.controller;

import com.appbraham.mediapp_backend.exception.ModeloNotFoundException;
import com.appbraham.mediapp_backend.model.Medico;
import com.appbraham.mediapp_backend.service.IMedicoService;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private IMedicoService service;

    @GetMapping
    public ResponseEntity<List<Medico>> listar() throws Exception {
        List<Medico> lista = service.listar();
        return new ResponseEntity<List<Medico>>( lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Medico medico = service.listarPorId(id);

        if(medico == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
        }

        return new ResponseEntity<Medico>(medico, HttpStatus.OK);
    }

    //Usando HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<Medico> ListarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {

        Medico medico = service.listarPorId(id);
        //localhost:8080/medico/{id}
        EntityModel<Medico> recurso = EntityModel.of(medico);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
        recurso.add(linkTo.withRel("medicoRecurso"));

        return recurso;
    }

    //@Valid, valida según las validaciones de nuestro modelo Medico
    //@RequestBody serializa el JSON a un obtejo de tipo Java
//    @PostMapping
//    public ResponseEntity<Medico> registrar(@Valid @RequestBody Medico medico){
//        Medico nuevoMedico = service.registrar(medico);
//        return new ResponseEntity<Medico>(nuevoMedico, HttpStatus.CREATED);
//    }

    //API Nivel 3 - Richardson
    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Medico medico) throws Exception {
        Medico nuevoMedico = service.registrar(medico);
        //localhost:8080/medico/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nuevoMedico.getIdMedico()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Medico> modificar(@Valid @RequestBody Medico medico) throws Exception {
        Medico nuevoMedico = service.modificar(medico);
        return new ResponseEntity<Medico>(nuevoMedico, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) throws Exception {
        Medico m = service.listarPorId(id);
        if (m == null){
            throw new ModeloNotFoundException("El Medico no existe, ID: " +id);
        }
        service.eliminar(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
