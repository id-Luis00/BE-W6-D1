package ediLuis.BE_W6_D1.payloads.ViaggiPayloads;


import jakarta.validation.constraints.NotEmpty;
import ediLuis.BE_W6_D1.enums.StatoViaggio;

import java.time.LocalDate;

public record ViaggioDTO(
        @NotEmpty(message = "Destinazione obbligatoria, sennò dove vai?")
        String destination,
        @NotEmpty(message = "Naturalmente la data è obbligatoria, bisogna essere organizzati")
        LocalDate date,
        @NotEmpty(message = "È obbligatorio inserire uno stato")
        @ValidEnum(enumClass = StatoViaggio.class, message = "Stato del viaggio non valido")
        StatoViaggio statoViaggio
) {}
