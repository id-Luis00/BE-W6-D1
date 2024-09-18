package ediLuis.BE_W6_D1.repositories;

import ediLuis.BE_W6_D1.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {

    boolean existsByViaggioDateAndDipendenteId(LocalDate dateBooking, UUID dipendenteId);

}
