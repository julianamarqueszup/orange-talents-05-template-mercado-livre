package br.com.zupacademy.juliana.mercadolivre.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsMapper {

    UserDetails map(Object shouldBeASystemUser);
}