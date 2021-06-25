package br.com.zupacademy.juliana.mercadolivre.model;

import br.com.zupacademy.juliana.mercadolivre.dto.ProductCharacteristicsDTO;
import br.com.zupacademy.juliana.mercadolivre.dto.ProductOutputDTO;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer amount;
    private String description;
    private BigDecimal value;
    @NotNull
    @ManyToOne
    private Category category;
    @ManyToOne
    private NewUser owner;
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private Set<ProductCharacteristics> characteristics = new HashSet<>();
    private OffsetDateTime timestamp = OffsetDateTime.now();

    @Deprecated
    public Product(@NotBlank String name, @Positive @NotNull Integer amount, @NotBlank @Size(max = 1000) String description, @Positive @NotNull BigDecimal value, Category category, NewUser owner, @Size(min = 3) @Valid List<ProductCharacteristicsDTO> characteristicsDTO) {
    }

    public Product(String name, Integer amount,
                   String description, BigDecimal value, Category category,
                   NewUser owner, List<ProductCharacteristics> characteristics) {
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.value = value;
        this.category = category;
        this.owner = owner;
        this.characteristics.addAll(characteristics.stream()
                .map(characteristicsDTO -> characteristicsDTO.toModel(this))
                .collect(Collectors.toSet()));
        Assert.isTrue(this.characteristics.size() >= 3, "Every product needs" +
                " have at least 3 characteristics");
    }

    public ProductOutputDTO toOutoutDTO() {
        return new ProductOutputDTO(id, name, amount, description,
                value, category, owner,
                characteristics, timestamp);
    }


    public Long getId() {
        return id;
    }

    public NewUser getOwner() {
        return owner;
    }

    public boolean belongsToUser(NewUser owner) {
        return this.owner.equals(owner);
    }

    public boolean decreaseStock(@Positive int amount) {
        Assert.isTrue(amount > 0, "\n" +
                "Quantity must be greater than zero " +
                "to decrease stock. Amount transferred: "+amount);

        if(amount <= this.amount) {
            this.amount -= amount;
            return true;
        }

        return false;
    }
}