package br.com.zupacademy.juliana.mercadolivre.controller;

import br.com.zupacademy.juliana.mercadolivre.dto.OpinionOutputDTO;
import br.com.zupacademy.juliana.mercadolivre.dto.OpinionDTO;
import br.com.zupacademy.juliana.mercadolivre.exception.BusinessException;
import br.com.zupacademy.juliana.mercadolivre.model.NewUser;
import br.com.zupacademy.juliana.mercadolivre.model.Opinion;
import br.com.zupacademy.juliana.mercadolivre.model.Product;
import br.com.zupacademy.juliana.mercadolivre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class OpinionController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/products/{id}/opinions")
    @Transactional
    public ResponseEntity<OpinionOutputDTO> add(@RequestBody @Valid OpinionDTO opinion,
                                                     @PathVariable("id") Long id){
        Product product = manager.find(Product.class, id);

        if(product == null) {
            throw new BusinessException("Selected product does not exist");
        }

        NewUser consumer = userRepository.findByEmail("adm@adm.com")
                .orElseThrow(() -> new BusinessException("User not found"));

        Opinion newOpinion = opinion.toModel(product, consumer);

        manager.persist(newOpinion);

        OpinionOutputDTO opinionOutputDTO = newOpinion.toOutputDTO();

        return ResponseEntity.ok(opinionOutputDTO);
    }
}