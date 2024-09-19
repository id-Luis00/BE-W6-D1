package ediLuis.BE_W6_D1.services;


import ediLuis.BE_W6_D1.entities.Dipendente;
import ediLuis.BE_W6_D1.payloads.LoginPayloads.LoginDTO;
import ediLuis.BE_W6_D1.payloads.LoginPayloads.LoginRespDTO;
import ediLuis.BE_W6_D1.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private DipendenteService dipendenteService;

    public LoginRespDTO checkCredentialsAndGenerateToken(LoginDTO body){
        // controlli generali, come l'esistenza dell'utente così può fare il login

        // cerco il dipendente sul db per email
        Dipendente found = this.dipendenteService.findByEmail(body.email());

        if (found != null) {
            return new LoginRespDTO(this.jwtTools.createToken(found));
        } else {
            throw new RuntimeException("Errore nella creazione del token");
        }

    }
}
