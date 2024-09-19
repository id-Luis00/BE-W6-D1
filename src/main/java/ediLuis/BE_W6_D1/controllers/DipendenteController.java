package ediLuis.BE_W6_D1.controllers;


import ediLuis.BE_W6_D1.entities.Dipendente;

import ediLuis.BE_W6_D1.payloads.DipendentiPayloads.DipendenteDTO;

import ediLuis.BE_W6_D1.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/Dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Dipendente> findAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size) {
        return this.dipendenteService.findAllDipendenti(page, size);
    }

    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable UUID dipendenteId) {
        return this.dipendenteService.findById(dipendenteId);
    }


    @PutMapping("/{dipendenteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Dipendente findAndUpdate(@PathVariable UUID dipendenteId, @RequestBody DipendenteDTO body) {
        return this.dipendenteService.findAndUpdate(dipendenteId, body);
    }

    @DeleteMapping("/{dipendenteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID dipendenteId) {
        this.dipendenteService.findAndDelete(dipendenteId);
    }

}
