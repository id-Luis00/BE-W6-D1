package ediLuis.BE_W6_D1.controllers;


import ediLuis.BE_W6_D1.entities.Viaggio;
import ediLuis.BE_W6_D1.payloads.ViaggiPayloads.ViaggioDTO;
import ediLuis.BE_W6_D1.payloads.ViaggiPayloads.ViaggioRespDTO;
import ediLuis.BE_W6_D1.payloads.ViaggiPayloads.ViaggioStatoDTO;
import ediLuis.BE_W6_D1.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/Viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;


    @GetMapping
    public Page<Viaggio> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size){
        return this.viaggioService.findAll(page, size);
    }

    @GetMapping("/{viaggioId}")
    public Viaggio findById(@PathVariable UUID viaggioId) {
        return this.viaggioService.findById(viaggioId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ViaggioRespDTO saveNewViaggio(@RequestBody ViaggioDTO body){
       return new ViaggioRespDTO(this.viaggioService.save(body).getId());
    }

    @PatchMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.OK)
    public Viaggio findAndUpdateStato(@PathVariable UUID viaggioId, @RequestBody ViaggioStatoDTO body){
        return this.viaggioService.findAndUpdateStato(viaggioId, body);
    }

    @PutMapping("/{viaggioId}")
    public Viaggio findAndUpdate(@PathVariable UUID viaggioId, @RequestBody ViaggioDTO body){
        return this.viaggioService.findAndUpdate(viaggioId, body);
    }

    @DeleteMapping("/{viaggioId}")
    public void findAndDelete(@PathVariable UUID viaggioId) {
        this.viaggioService.findAndDelete(viaggioId);
    }



}
