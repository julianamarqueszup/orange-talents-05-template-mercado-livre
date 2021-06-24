package br.com.zupacademy.juliana.mercadolivre.controller;

import br.com.zupacademy.juliana.mercadolivre.dto.QuestionDTO;
import br.com.zupacademy.juliana.mercadolivre.dto.QuestionOutputDTO;
import br.com.zupacademy.juliana.mercadolivre.exception.BusinessException;
import br.com.zupacademy.juliana.mercadolivre.model.Emails;
import br.com.zupacademy.juliana.mercadolivre.model.NewUser;
import br.com.zupacademy.juliana.mercadolivre.model.Product;
import br.com.zupacademy.juliana.mercadolivre.model.Question;
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
public class QuestionController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Emails emails;

    @PostMapping("/products/{id}/questions")
    @Transactional
    public ResponseEntity<QuestionOutputDTO> create(@RequestBody @Valid QuestionDTO questionDTO
            , @PathVariable("id") Long id){
        Product product = manager.find(Product.class, id);

        if(product == null){
            throw new BusinessException("Selected product does not exist");
        }

        NewUser newUser = userRepository.findByEmail("b@b.com")
                .orElseThrow(() -> new BusinessException("User not " +
                        "found"));

        Question question = questionDTO.toModel(newUser, product);

        manager.persist(question);

        QuestionOutputDTO questionOutputDTO = question.toOutputDTO();

        emails.send(question);

        return ResponseEntity.ok(questionOutputDTO);

    }
}