package br.com.zupacademy.juliana.mercadolivre.model;

import br.com.zupacademy.juliana.mercadolivre.dto.UserDTO;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "users")
public class NewUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private Instant timestamp;

    @Deprecated
    public NewUser() {
    }

    public NewUser(String email, @Valid @NotNull CleanPassword cleanPassword) {
        Assert.isTrue(StringUtils.hasLength(email), "Email cannot be blank");
        Assert.notNull(cleanPassword, "Object of type clear password cannot be null");
        this.email = email;
        this.password = cleanPassword.hash();
        this.timestamp = Instant.now();
    }

    public UserDTO toDTO() {
        return new UserDTO(email, password, timestamp);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}