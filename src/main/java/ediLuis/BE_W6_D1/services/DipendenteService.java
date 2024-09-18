package ediLuis.BE_W6_D1.services;


import ediLuis.BE_W6_D1.entities.Dipendente;
import ediLuis.BE_W6_D1.exceptions.BadRequestException;
import ediLuis.BE_W6_D1.exceptions.NotFoundException;
import ediLuis.BE_W6_D1.payloads.DipendentiPayloads.DipendenteDTO;
import ediLuis.BE_W6_D1.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Page<Dipendente> findAllDipendenti(int page, int size){

        Pageable pageable = PageRequest.of(page, size);

        return this.dipendenteRepository.findAll(pageable);
    }

    public Dipendente findById(UUID id){
        return this.dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Il dipendente con id: " + id + " non è stato trovato"));
    }

    public Dipendente save(DipendenteDTO body){
        // controlliamo che l'email non esista di già
        if (this.dipendenteRepository.existsByEmail(body.email())) throw new BadRequestException("L'email esiste già, riprovare con una nuova email");

        // controlliamo che non esista già lo username
        if (this.dipendenteRepository.existsByUsername(body.username())) throw new BadRequestException("Questo username esiste già, riprovare con un nuovo username");

        // inseriamo i dati server-generated... dopo

        // salviamo il tutto
        Dipendente newDipendente = new Dipendente(body.username(), body.name(), body.surname(), body.email());

        return this.dipendenteRepository.save(newDipendente);
    }


    public Dipendente findAndUpdate(UUID dipendenteId, DipendenteDTO body){
        Dipendente foundDipendente = this.findById(dipendenteId);
        if (this.dipendenteRepository.existsByUsername(body.username())) throw new BadRequestException("Username già utilizzato!");
        foundDipendente.setUsername(body.username());
        foundDipendente.setName(body.name());
        foundDipendente.setSurname(body.surname());
        if (this.dipendenteRepository.existsByEmail(body.email())) throw new BadRequestException("Email già utilizzata, provare con un'altra");
        foundDipendente.setEmail(body.email());

        return foundDipendente;

    }

    public void findAndDelete(UUID dipendenteId) {
        this.dipendenteRepository.deleteById(dipendenteId);
    }




}
