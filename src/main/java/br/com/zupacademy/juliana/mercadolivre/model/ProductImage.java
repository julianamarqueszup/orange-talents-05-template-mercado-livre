package br.com.zupacademy.juliana.mercadolivre.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Valid
    @ManyToOne
    private Product product;
    @URL
    @NotBlank
    private String link;

    @Deprecated
    public ProductImage() {
    }

    public ProductImage(@NotNull @Valid Product product, @URL @NotBlank String link) {
        this.product = product;
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}