package br.com.zupacademy.juliana.mercadolivre.controller;

import br.com.zupacademy.juliana.mercadolivre.dto.CategoryDTO;
import br.com.zupacademy.juliana.mercadolivre.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @PersistenceContext
    EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid CategoryDTO categoryDTO) {
        Category category = categoryDTO.toModel(manager);
        manager.persist(category);
        return ResponseEntity.ok().build();
    }
}