package br.com.zupacademy.juliana.mercadolivre.dto;

import br.com.zupacademy.juliana.mercadolivre.exception.BusinessException;
import br.com.zupacademy.juliana.mercadolivre.model.Category;
import br.com.zupacademy.juliana.mercadolivre.model.NewUser;
import br.com.zupacademy.juliana.mercadolivre.model.Product;
import br.com.zupacademy.juliana.mercadolivre.validation.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
    @NotBlank
    @UniqueValue(domainClass = Product.class, fieldName = "name")
    private String name;
    @Positive
    @NotNull
    private BigDecimal value;
    @Positive
    @NotNull
    private Integer amount;
    @Size(min = 3)
    @Valid
    private List<ProductCharacteristicsDTO> characteristics =
            new ArrayList<>();
    @NotBlank
    @Size(max = 1000)
    private String description;
    @NotNull
    private Long idCategory;

    public ProductDTO(String name, BigDecimal value, Integer amount,
                      String description, Long idCategory) {
        this.name = name;
        this.value = value;
        this.amount = amount;
        this.description = description;
        this.idCategory = idCategory;
    }

    public List<ProductCharacteristicsDTO> getCharacteristics() {
        return characteristics;
    }

    public Product toModel(EntityManager manager, NewUser owner) {
        Category category = manager.find(Category.class, idCategory);

        if (category == null) {
            throw new BusinessException("Category entered does not exist");
        }

        return new Product(name, amount, description, value, category,
                owner, characteristics);
    }
}
