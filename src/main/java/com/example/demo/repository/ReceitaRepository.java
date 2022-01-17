package com.example.demo.repository;

import com.example.demo.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Integer> {

    public List<Receita> findByDescription(String description);
}
