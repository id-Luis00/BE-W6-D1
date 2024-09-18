package ediLuis.BE_W6_D1.controllers;


import ediLuis.BE_W6_D1.entities.Prenotazione;
import ediLuis.BE_W6_D1.payloads.PrenotazioniPayloads.PrenotazioneDTO;
import ediLuis.BE_W6_D1.payloads.PrenotazioniPayloads.PrenotazioneRespDTO;
import ediLuis.BE_W6_D1.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/Prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Prenotazione> findAll(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size) {
        return this.prenotazioneService.findAll(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneRespDTO newPrenotazione(@RequestBody PrenotazioneDTO body){
        return new PrenotazioneRespDTO(this.prenotazioneService
                .saveNewPrenotazione(body,UUID.fromString(body.dipendenteId()),UUID.fromString(body.viaggioId()))
                .getId());
    }

    @PutMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.OK)
    public Prenotazione findAndUpdate(@PathVariable UUID prenotazioneId, @RequestBody PrenotazioneDTO body){
        return this.prenotazioneService.findAndUpdate(prenotazioneId, body);
    }

    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID prenotazioneId){
        this.prenotazioneService.findAndDelete(prenotazioneId);
    }




}
