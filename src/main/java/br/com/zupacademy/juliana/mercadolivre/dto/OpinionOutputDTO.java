package br.com.zupacademy.juliana.mercadolivre.dto;

import br.com.zupacademy.juliana.mercadolivre.model.NewUser;
import br.com.zupacademy.juliana.mercadolivre.model.Product;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OpinionOutputDTO {
    @Min(1) @Max(5)
    private int note;
    @NotBlank
    private String title;
    @NotBlank
    @Size(max = 500)
    private String description;
    private Long idProduct;
    private Long idConsumer;

    public OpinionOutputDTO(int note, String title, String description,
                            Product product, NewUser consumer) {
        this.note = note;
        this.title = title;
        this.description = description;
        this.idProduct = product.getId();
        this.idConsumer = consumer.getId();
    }

    public int getNote() { return note;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getIdProduct() { return idProduct;
    }

    public Long getIdConsumer() {
        return idConsumer;
    }
}