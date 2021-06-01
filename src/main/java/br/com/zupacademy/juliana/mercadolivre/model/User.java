package br.com.zupacademy.juliana.mercadolivre.model;

import br.com.zupacademy.juliana.mercadolivre.dto.UserDTO;
import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Instant timestamp;

    @Deprecated
    public User() {
    }

    public User(String email) {
        this.email = email;
        this.timestamp = Instant.now();
    }
    public UserDTO toDTO() {
        return new UserDTO(email, timestamp);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}


