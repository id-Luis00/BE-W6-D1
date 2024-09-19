package ediLuis.BE_W6_D1.repositories;


import ediLuis.BE_W6_D1.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, UUID> {
    Optional<Dipendente> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
