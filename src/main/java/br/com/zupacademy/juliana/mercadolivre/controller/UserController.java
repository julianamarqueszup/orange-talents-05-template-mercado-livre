package br.com.zupacademy.juliana.mercadolivre.controller;

import br.com.zupacademy.juliana.mercadolivre.dto.UserDTO;
import br.com.zupacademy.juliana.mercadolivre.model.NewUser;
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
@RequestMapping("/users")
public class UserController {
    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO userDTO){
        NewUser user = userDTO.toModel();
        entityManager.persist(user);
        return ResponseEntity.ok().build();
    }
}