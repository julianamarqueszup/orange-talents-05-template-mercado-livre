package br.com.zupacademy.juliana.mercadolivre.controller;

import br.com.zupacademy.juliana.mercadolivre.dto.ProductDTO;
import br.com.zupacademy.juliana.mercadolivre.dto.ProductOutputDTO;
import br.com.zupacademy.juliana.mercadolivre.exception.BusinessException;
import br.com.zupacademy.juliana.mercadolivre.model.NewUser;
import br.com.zupacademy.juliana.mercadolivre.model.Product;
import br.com.zupacademy.juliana.mercadolivre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ProductOutputDTO> save(@RequestBody @Valid ProductDTO productDTO) {
        NewUser owner = userRepository.findByEmail("adm@adm.com")
                .orElseThrow(() -> new BusinessException("User not found"));

        Product product = productDTO.toModel(manager, owner);
        manager.persist(product);

        ProductOutputDTO productOutputDTO = product.toOutoutDTO();

        return ResponseEntity.ok(productOutputDTO);
    }

}