package br.com.zupacademy.juliana.mercadolivre.model;

import br.com.zupacademy.juliana.mercadolivre.dto.ProductCharacteristicsDTO;
import br.com.zupacademy.juliana.mercadolivre.dto.ProductOutputDTO;
import org.springframework.util.Assert;

import javax.crypto.spec.OAEPParameterSpec;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private Set<ProductImage> image = new HashSet<>();
    @OneToMany(mappedBy = "product")
    @OrderBy("title asc")
    private SortedSet<Question> questions = new TreeSet<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private List<Opinion> opinion = new ArrayList<>();

    @Deprecated
    public Product() {
    }

    public Product(String name, Integer amount,
                   String description, BigDecimal value, Category category,
                   NewUser owner, List<ProductCharacteristicsDTO> characteristicsDTOS) {
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.value = value;
        this.category = category;
        this.owner = owner;
        this.characteristics.addAll(characteristics.stream()
                .map(characteristics -> characteristics.toModel(this))
                .collect(Collectors.toSet()));
        Assert.isTrue(this.characteristics.size() >= 3, "Todo produto precisa" +
                " ter no mínimo 3 características");
    }

    public ProductOutputDTO toOutoutDTO() {
        return new ProductOutputDTO(id, name,amount, description,
                value, category, owner,
                characteristics, timestamp, image);
    }

    public void associateImage(Set<String> links) {
        Set<ProductImage> image = links.stream()
                .map(link -> new ProductImage(this, link))
                .collect(Collectors.toSet());

        this.image.addAll(image);
    }

    public void setImage(Set<ProductImage> image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public NewUser getOwner() {
        return owner;
    }

    public boolean belongsUser(NewUser owner) {
        return this.owner.equals(owner);
    }

    public List<Opinion> getOpinions() {
        return opinion;
    }

    public SortedSet<Question> getQuestions() {
        return questions;
    }

    public boolean decreaseStock(@Positive int amount) {
        Assert.isTrue(amount > 0, "A quantidade deve ser maior que zero " +
                "para abater o estoque. Quantidade repassada: "+amount);

        if(amount <= this.amount) {
            this.amount -= amount;
            return true;
        }

        return false;
    }
}