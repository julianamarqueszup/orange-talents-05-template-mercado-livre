package br.com.zupacademy.juliana.mercadolivre.repository;

import br.com.zupacademy.juliana.mercadolivre.model.NewUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<NewUser, Long> {
    public Optional<NewUser> findByEmail(String email);
}
