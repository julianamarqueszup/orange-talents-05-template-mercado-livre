  
package br.com.zupacademy.juliana.mercadolivre.dto;

import br.com.zupacademy.juliana.mercadolivre.model.CleanPassword;
import br.com.zupacademy.juliana.mercadolivre.model.NewUser;
import br.com.zupacademy.juliana.mercadolivre.validation.UniqueValue;
import org.apache.catalina.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

public class UserDTO {
    @NotBlank
    @Email
    @UniqueValue(domainClass = NewUser.class, fieldName = "email")
    private String email;
    @Size(min = 6)
    @NotBlank
    private String password;
    private Instant timestamp;

    @Deprecated
    public UserDTO() {
    }

    public UserDTO(@NotBlank @Email String email,@Size(min = 6) @NotBlank String password, Instant timestamp) {
        this.email = email;
        this.password = password;
        this.timestamp = timestamp;
    }

    public NewUser toModel(){
        return new NewUser(email, new CleanPassword(password));
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
