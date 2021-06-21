package br.com.zupacademy.juliana.mercadolivre.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

public class CleanPassword {
    private String password;

    public CleanPassword(String senha) {
        Assert.hasLength(senha, "Password cannot be blank");
        Assert.isTrue(senha.length() >= 6, "Password must be at least 6 characters");
        this.password = password;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(password);
    }
}