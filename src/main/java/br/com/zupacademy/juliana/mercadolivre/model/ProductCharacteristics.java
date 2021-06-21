package br.com.zupacademy.juliana.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class ProductCharacteristics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @ManyToOne
    private Product product;

    @Deprecated
    public ProductCharacteristics() {
    }

    public ProductCharacteristics(String name, String description, Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}