package br.com.zupacademy.juliana.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @ManyToOne
    private Category categoryMaster;

    @Deprecated
    public Category() {
    }

    public Category(String name, Category categoryMaster) {
        this.name = name;
        this.categoryMaster = categoryMaster;
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}