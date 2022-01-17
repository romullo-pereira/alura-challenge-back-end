package com.example.demo.services;

import com.example.demo.model.Receita;
import com.example.demo.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository repository;
    
    public Boolean save(Receita receita) {

        Optional<List<Receita>> receitas = Optional.ofNullable(repository.findByDescription(receita.getDescription())); 
        
        if(receitas.isPresent()) {
            List<Receita> persistedReceitas = receitas.get();

            for (Receita r: persistedReceitas) {
                if(r.getDate().getMonth() == receita.getDate().getMonth()) {
                    return false;
                }
            }
        }
        repository.save(receita);
        return true;

    }
}
