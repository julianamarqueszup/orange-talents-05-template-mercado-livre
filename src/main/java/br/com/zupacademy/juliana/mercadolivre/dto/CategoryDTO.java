package br.com.zupacademy.juliana.mercadolivre.dto;

import br.com.zupacademy.juliana.mercadolivre.exception.ValidationException;
import br.com.zupacademy.juliana.mercadolivre.model.Category;
import br.com.zupacademy.juliana.mercadolivre.validation.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoryDTO {
    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldName = "name")
    private String name;
    @Positive
    private Long idCategoryMaster;

    public CategoryDTO(String name, Long idCategoryMaster) {
        this.name = name;
        this.idCategoryMaster = idCategoryMaster;
    }

    @Override
    public String toString() {
        return "CategoryDTO{name='" + name + '\'' + ", idCategoryMaster=" + idCategoryMaster + '}';
    }

    public Category toModel(EntityManager manager) {
        if (idCategoryMaster != null) {
            Category categoryMaster = manager.find(Category.class,
                    idCategoryMaster);
            if(categoryMaster == null) {
                throw new ValidationException("The category master id must be valid.");
            }
            Assert.notNull(categoryMaster, "The category master id must be valid.");
            return new Category(name, categoryMaster);
        }
        return new Category(name);
    }
}