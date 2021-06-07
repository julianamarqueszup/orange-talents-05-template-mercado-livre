package br.com.zupacademy.juliana.mercadolivre.repository;

import br.com.zupacademy.juliana.mercadolivre.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
