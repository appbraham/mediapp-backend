package com.appbraham.mediapp_backend.controller;

import com.appbraham.mediapp_backend.dto.ConsultaListaExamenDTO;
import com.appbraham.mediapp_backend.exception.ModeloNotFoundException;
import com.appbraham.mediapp_backend.model.Consulta;
import com.appbraham.mediapp_backend.service.IConsultaService;
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
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private IConsultaService service;

    @GetMapping
    public ResponseEntity<List<Consulta>> listar() {
        List<Consulta> lista = service.listar();
        return new ResponseEntity<List<Consulta>>( lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> listarPorId(@PathVariable("id") Integer id){
        Consulta consulta = service.listarPorId(id);

        if(consulta.getIdConsulta() == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
        }

        return new ResponseEntity<Consulta>(consulta, HttpStatus.OK);
    }

    //Usando HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<Consulta> ListarPorIdHateoas(@PathVariable("id") Integer id){

        Consulta consulta = service.listarPorId(id);
        //localhost:8080/consulta/{id}
        EntityModel<Consulta> recurso = EntityModel.of(consulta);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
        recurso.add(linkTo.withRel("consultaRecurso"));

        return recurso;
    }

    //@Valid, valida según las validaciones de nuestro modelo Consulta
    //@RequestBody serializa el JSON a un obtejo de tipo Java
//    @PostMapping
//    public ResponseEntity<Consulta> registrar(@Valid @RequestBody Consulta consulta){
//        Consulta nuevoConsulta = service.registrar(consulta);
//        return new ResponseEntity<Consulta>(nuevoConsulta, HttpStatus.CREATED);
//    }

    //API Nivel 3 - Richardson
    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody ConsultaListaExamenDTO dto){
        Consulta nuevoConsulta = service.registrarTransaccional(dto);
        //localhost:8080/consulta/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nuevoConsulta.getIdConsulta()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Consulta> modificar(@Valid @RequestBody Consulta consulta){
        Consulta nuevoConsulta = service.modificar(consulta);
        return new ResponseEntity<Consulta>(nuevoConsulta, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
        Consulta m = service.listarPorId(id);
        if (m.getIdConsulta() == null){
            throw new ModeloNotFoundException("El Consulta no existe, ID: " +id);
        }
        service.eliminar(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
