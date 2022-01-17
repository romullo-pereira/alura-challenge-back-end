package com.example.demo.controller;

import com.example.demo.model.Receita;
import com.example.demo.repository.ReceitaRepository;
import com.example.demo.services.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaRepository repository;

    @Autowired
    private ReceitaService service;

    @GetMapping
    public ResponseEntity getReceitas() {
        List<Receita> receitas = repository.findAll();

        if(!receitas.isEmpty()) {
           return ResponseEntity.ok().body(receitas);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getReceitasById(@PathVariable Integer id) {
        Optional<Receita> receitaOpt = repository.findById(id);

        if(receitaOpt.isPresent()) {
            return ResponseEntity.ok().body(receitaOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity createReceitas(@RequestBody Receita receita) {
        if(service.save(receita)) {
            return ResponseEntity.status(201).build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateReceitas(@PathVariable Integer id,
                                         @RequestBody Receita receita) {
        Optional<Receita> receitaOpt = repository.findById(id);

        if(receitaOpt.isPresent()) {
            receita.setId(id);
               if(service.save(receita)) {
                   return ResponseEntity.ok().build();
               }
               return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReceita(@PathVariable Integer id) {

        Optional<Receita> receitaOpt = repository.findById(id);

        if(receitaOpt.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

}
