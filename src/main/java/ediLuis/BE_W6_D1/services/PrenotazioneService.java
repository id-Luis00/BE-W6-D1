package ediLuis.BE_W6_D1.services;

import ediLuis.BE_W6_D1.entities.Dipendente;
import ediLuis.BE_W6_D1.entities.Prenotazione;
import ediLuis.BE_W6_D1.entities.Viaggio;
import ediLuis.BE_W6_D1.exceptions.BadRequestException;
import ediLuis.BE_W6_D1.exceptions.NotFoundException;
import ediLuis.BE_W6_D1.payloads.PrenotazioniPayloads.PrenotazioneDTO;
import ediLuis.BE_W6_D1.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private ViaggioService viaggioService;


    public Page<Prenotazione> findAll(int page, int size){

        Pageable pageable = PageRequest.of(page, size);

        return this.prenotazioneRepository.findAll(pageable);

    }

    public Prenotazione findById(UUID prenotazioneId) {
        return this.prenotazioneRepository.findById(prenotazioneId).orElseThrow(() -> new NotFoundException("La prenotazione " + prenotazioneId + " non è stata trovata"));
    }

    public Prenotazione saveNewPrenotazione(PrenotazioneDTO body, UUID dipendenteId, UUID viaggioId) {

        // 1.1 controllare l'esistenza del dipendente
        Dipendente foundDipendente = this.dipendenteService.findById(dipendenteId);


        // 1.2 controllare l'esistenza del viaggio
        Viaggio viaggioFound = this.viaggioService.findById(viaggioId);

        // 1.3 un dipendente non può avere due prenotazioni lo stesso giorno (ho capito finalmente queste derived queries :))) )
        if (this.prenotazioneRepository.existsByViaggioDateAndDipendenteId(viaggioFound.getDate(), dipendenteId)) throw new BadRequestException("Il dipendente " + dipendenteId + " ha già una prenotazione per il giorno " + viaggioFound.getDate());


        // 2. creare una prenotazione
        Prenotazione newPrenotazione = new Prenotazione(body.dateBooking(), body.preferences(), foundDipendente, viaggioFound);

        // 2.1 salvare la prenotazione
        this.prenotazioneRepository.save(newPrenotazione);

        // 3. tornare la prenotazione pronta
        return newPrenotazione;
    }

    public Prenotazione findAndUpdate(UUID prenotazioneId, PrenotazioneDTO body){
        Prenotazione foundPrenotazione = this.findById(prenotazioneId);
        foundPrenotazione.setDateBooking(body.dateBooking());
        foundPrenotazione.setPreferences(body.preferences());

        Dipendente foundDipendente = this.dipendenteService.findById(UUID.fromString(body.dipendenteId()));
        foundPrenotazione.setDipendente(foundDipendente);

        Viaggio viaggioFound = this.viaggioService.findById(UUID.fromString(body.viaggioId()));
        foundPrenotazione.setViaggio(viaggioFound);

        this.prenotazioneRepository.save(foundPrenotazione);

        return foundPrenotazione;
    }

    public void findAndDelete(UUID prenotazioneId){
        this.prenotazioneRepository.deleteById(prenotazioneId);
    }
}
