package ediLuis.BE_W6_D1.controllers;


import ediLuis.BE_W6_D1.entities.Dipendente;
import ediLuis.BE_W6_D1.exceptions.BadRequestException;
import ediLuis.BE_W6_D1.payloads.DipendentiPayloads.DipendenteDTO;
import ediLuis.BE_W6_D1.payloads.DipendentiPayloads.DipendenteRespDTO;
import ediLuis.BE_W6_D1.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    public Page<Dipendente> findAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size){
        return this.dipendenteService.findAllDipendenti(page, size);
    }

    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable UUID dipendenteId){
        return this.dipendenteService.findById(dipendenteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteRespDTO saveNew(@RequestBody @Validated DipendenteDTO body, BindingResult validationRes){

        if (validationRes.hasErrors()){
            String errorMessage = validationRes.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(" - "));

            throw new BadRequestException("errore nella validazione -> " + errorMessage);
        }

        return new DipendenteRespDTO(this.dipendenteService.save(body).getId());
    }

    @PutMapping("/{dipendenteId}")
    public Dipendente findAndUpdate(@PathVariable UUID dipendenteId, @RequestBody DipendenteDTO body){
        return this.dipendenteService.findAndUpdate(dipendenteId, body);
    }

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID dipendenteId) {
        this.dipendenteService.findAndDelete(dipendenteId);
    }

}
