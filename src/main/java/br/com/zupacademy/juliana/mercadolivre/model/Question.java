package br.com.zupacademy.juliana.mercadolivre.model;
import br.com.zupacademy.juliana.mercadolivre.dto.QuestionOutputDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Entity
public class Question implements Comparable<Question> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @NotNull
    @ManyToOne
    private NewUser newUser;
    @NotNull
    @ManyToOne
    private Product product;
    private OffsetDateTime timestamp;

    @Deprecated
    public Question() {
    }

    public Question(String title, NewUser newUser, Product product) {
        this.title = title;
        this.newUser = newUser;
        this.product = product;
        this.timestamp = OffsetDateTime.now();
    }

    public QuestionOutputDTO toOutputDTO() {
        return new QuestionOutputDTO(title, newUser, product, timestamp);
    }

    public NewUser getNewUser() {
        return newUser;
    }

    public NewUser getOwnerProduct() {
        return product.getOwner();
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(Question o) {
        return this.title.compareTo(o.title);
    }
}