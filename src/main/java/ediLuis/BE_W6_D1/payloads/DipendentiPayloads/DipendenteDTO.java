package ediLuis.BE_W6_D1.payloads.DipendentiPayloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(
        @NotEmpty(message = "Lo username è obbligatorio")
        @Size(min = 4, max = 16, message = "La dimensione dello username dev'essere compresa tra 4 e 16")
        String username,
        @NotEmpty(message = "Nome è obbligatorio")
        String name,
        @NotEmpty(message = "Cognome è obbligatorio")
        String surname,
        @NotEmpty(message = "Email è obbligatoria")
        @Email(message = "Formato dell'email non valido")
        String email
) {
}
