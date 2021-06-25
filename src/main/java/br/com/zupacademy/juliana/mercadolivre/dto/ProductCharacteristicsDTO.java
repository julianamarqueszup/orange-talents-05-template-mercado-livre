package br.com.zupacademy.juliana.mercadolivre.dto;

import br.com.zupacademy.juliana.mercadolivre.model.Product;
import br.com.zupacademy.juliana.mercadolivre.model.ProductCharacteristics;

import javax.validation.constraints.NotBlank;

public class ProductCharacteristicsDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    public ProductCharacteristicsDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProductCharacteristicsDTO(ProductCharacteristics characteristicsDTO) {
        this.name = characteristicsDTO.getName();
        this.description = characteristicsDTO.getDescription();
    }

    public ProductCharacteristics toModel(Product product){
        return new ProductCharacteristics(name, description, product);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}