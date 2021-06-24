package br.com.zupacademy.juliana.mercadolivre.model;

import br.com.zupacademy.juliana.mercadolivre.dto.OpinionOutputDTO;

import javax.persistence.*;

@Entity
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int note;
    private String title;
    private String description;
    @ManyToOne
    private Product product;
    @ManyToOne
    private NewUser consumer;

    @Deprecated
    public Opinion() {
    }

    public Opinion(int note, String title, String description, Product product, NewUser consumer) {
        this.note = note;
        this.title = title;
        this.description= description;
        this.product = product;
        this.consumer = consumer;
    }

    public OpinionOutputDTO toOutputDTO() {
        return new OpinionOutputDTO(note, title, description, product,
                consumer);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getNote() {
        return note;
    }
}