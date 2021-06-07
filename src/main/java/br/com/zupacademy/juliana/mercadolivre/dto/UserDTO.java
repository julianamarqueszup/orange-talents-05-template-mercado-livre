  
package br.com.zupacademy.juliana.mercadolivre.dto;

import br.com.zupacademy.juliana.mercadolivre.model.User;
import br.com.zupacademy.juliana.mercadolivre.validation.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

public class UserDTO {
    @NotBlank
    @Email @UniqueValue(domainClass = User.class,fieldName = "email")
    private String email;
    @NotBlank
    private Instant timestamp;

    @Deprecated
    public UserDTO(String email) {
    }

    public UserDTO(@NotBlank @Email String email, Instant timestamp) {
        this.email = email;
        this.timestamp = timestamp;
    }

    public User toModel(){
        return new User(email);
    }


    public String getEmail() {
        return email;
    }



    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

