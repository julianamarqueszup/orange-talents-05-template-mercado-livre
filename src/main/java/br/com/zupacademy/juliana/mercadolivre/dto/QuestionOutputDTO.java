package br.com.zupacademy.juliana.mercadolivre.dto;

import br.com.zupacademy.juliana.mercadolivre.model.NewUser;
import br.com.zupacademy.juliana.mercadolivre.model.Product;

import java.time.OffsetDateTime;

public class QuestionOutputDTO {
    private String title;
    private String newUser;
    private Long idProduct;
    private OffsetDateTime timestamp;

    public QuestionOutputDTO(String title, NewUser newUser,
                             Product product, OffsetDateTime timestamp) {
        this.title = title;
        this.newUser = newUser.getEmail();
        this.idProduct = product.getId();
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getNewUser() {
        return newUser;
    }

    public Long getProduct() {
        return idProduct;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }
}