package br.com.zupacademy.juliana.mercadolivre.model;

import br.com.zupacademy.juliana.mercadolivre.dto.ProductCharacteristicsDTO;
import br.com.zupacademy.juliana.mercadolivre.dto.ProductOutputDTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ProductDetail {
    private final Set<Map<String, String>> opinion;
    private final long totalOpinion;
    private final double averageGrades;
    private final SortedSet<String> question;
    private String name;
    private BigDecimal value;
    private Integer amount;
    private Set<ProductCharacteristics> characteristicsDTO;
    private String description;
    private String category;
    private String owner;
    private OffsetDateTime timestamp;
    private Set<String> linksImages;

    public ProductDetail(Product product) {
        ProductOutputDTO productoutputDTO = product.toOutoutDTO();
        this.name = productoutputDTO.getName();
        this.value = productoutputDTO.getValue();
        this.amount = productoutputDTO.getAmount();
        this.characteristicsDTO = productoutputDTO.getCharacteristicsDTO().stream().map(ProductCharacteristicsDTO::new).collect(Collectors.toSet());
        this.description = productoutputDTO.getDescription();
        this.category = productoutputDTO.getCategory();
        this.owner = productoutputDTO.getOwner();
        this.timestamp = productoutputDTO.getTimestamp();
        this.linksImages = productoutputDTO.getImage().stream().map(image -> new String(image.getLink())).collect(Collectors.toSet());

        List<Opinion> opinion = product.getOpinion();

        this.opinion = opinion.stream().map(opinion -> {
            return Map.of("title", opinion.getTitle(), "description", opinion.getDescription());
        }).collect(Collectors.toSet());

        OptionalDouble possibleMedia = opinion.stream().map(opiniao -> opiniao.getGrades()).mapToInt(grades -> grades).average();

        this.averageGrades = possibleMedia.orElse(0.0);

        this.question = product.getQuestion().stream().map(question -> question.getTitle()).collect(Collectors.toCollection(TreeSet::new));

        this.totalOpinion = opinion.stream().count();

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

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getOwner() {
        return owner;
    }

    public Set<ProductCharacteristics> getCharacteristicsDTO() {
        return characteristicsDTO;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public Set<Map<String, String>> getOpinion() {
        return opinion;
    }

    public long getTotalOpinion() {
        return totalOpinion;
    }

    public double getAverageGrades() {
        return averageGrades;
    }

    public Set<String> getQuestion() {
        return question;
    }

    public Set<String> getLinksImage() {
        return linksImage;
    }
}
