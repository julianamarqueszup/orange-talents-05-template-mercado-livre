package br.com.zupacademy.juliana.mercadolivre.dto;

import br.com.zupacademy.juliana.mercadolivre.model.NewUser;
import br.com.zupacademy.juliana.mercadolivre.model.Product;
import br.com.zupacademy.juliana.mercadolivre.model.Question;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class QuestionDTO {

    @NotBlank
    private String title;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public QuestionDTO(String title) {
        this.title = title;
    }

    public Question toModel(NewUser newUser, Product product) { return new Question(title, newUser, product);
    }
}