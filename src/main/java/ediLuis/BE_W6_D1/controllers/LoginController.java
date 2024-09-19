package ediLuis.BE_W6_D1.controllers;


import ediLuis.BE_W6_D1.exceptions.BadRequestException;
import ediLuis.BE_W6_D1.payloads.DipendentiPayloads.DipendenteDTO;
import ediLuis.BE_W6_D1.payloads.DipendentiPayloads.DipendenteRespDTO;
import ediLuis.BE_W6_D1.payloads.LoginPayloads.LoginDTO;
import ediLuis.BE_W6_D1.payloads.LoginPayloads.LoginRespDTO;
import ediLuis.BE_W6_D1.services.AuthService;
import ediLuis.BE_W6_D1.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthService authService;

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody LoginDTO body) {
        return this.authService.checkCredentialsAndGenerateToken(body);
    }

    @PostMapping("/register")
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

    //eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3MjY3NjQwMDMsImV4cCI6MTcyNzM2ODgwMywic3ViIjoiZDc5M2I5N2YtOGY5OS00MzE5LWE0ZTctNzgzNjEwYmM4OTk4In0.QLQ5SsKHZkWzOVX6kLwRgDWhHuH40gI6FrMd9XX7AfyOFAYXYF-i3epNXjg0Gq47X1IdLAij9Uy5mVt_vRHV5A

}
