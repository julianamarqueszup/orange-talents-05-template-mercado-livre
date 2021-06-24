package br.com.zupacademy.juliana.mercadolivre.dto;

import br.com.zupacademy.juliana.mercadolivre.model.NewUser;
import br.com.zupacademy.juliana.mercadolivre.model.Opinion;
import br.com.zupacademy.juliana.mercadolivre.model.Product;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OpinionDTO {
    @Min(1) @Max(5)
    private int note;
    @NotBlank
    private String title;
    @NotBlank
    @Size(max = 500)
    private String description;

    public OpinionDTO(int note, String title, String description) {
        this.note = note;
        this.title = title;
        this.description = description;
    }

    public Opinion toModel(Product product, NewUser consumer) {
        return new Opinion(note, title, description, product, consumer);
    }
}