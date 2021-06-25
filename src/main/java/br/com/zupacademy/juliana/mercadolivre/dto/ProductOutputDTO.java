package br.com.zupacademy.juliana.mercadolivre.dto;

import br.com.zupacademy.juliana.mercadolivre.model.Category;
import br.com.zupacademy.juliana.mercadolivre.model.NewUser;
import br.com.zupacademy.juliana.mercadolivre.model.ProductCharacteristics;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

public class ProductOutputDTO {
    private Long id;
    private String name;
    private BigDecimal value;
    private Integer amount;
    private Set<ProductCharacteristics> characteristicsDTO;
    private String description;
    private String category;
    private String owner;
    private OffsetDateTime timestamp;

    public ProductOutputDTO(Long id, String name, Integer amount,
                            String description, BigDecimal value, Category category,
                            NewUser owner,
                            Set<ProductCharacteristics> characteristicsDTO,
                            OffsetDateTime timestamp) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.amount = amount;
        this.characteristicsDTO = characteristicsDTO;
        this.description = description;
        this.category = category.getName();
        this.owner = owner.getEmail();
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Integer getAmount() {
        return amount;
    }

    public Set<ProductCharacteristics> getCharacteristicsDTO() {
        return characteristicsDTO;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getOwner() {
        return owner;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

}

