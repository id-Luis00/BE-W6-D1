package ediLuis.BE_W6_D1.payloads.ViaggiPayloads;

import jakarta.validation.constraints.NotEmpty;
import ediLuis.BE_W6_D1.enums.StatoViaggio;

public record ViaggioStatoDTO(
        @NotEmpty(message = "È obbligatorio inserire uno stato")
        @ValidEnum(enumClass = StatoViaggio.class, message = "Stato del viaggio non valido")
        StatoViaggio statoViaggio
) {
}
