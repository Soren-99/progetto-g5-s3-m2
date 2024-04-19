package sorenrahimi.progetto.g5s3m2.payload.events;


import jakarta.validation.constraints.NotNull;

import java.util.Date;


public record NewEventPayload(
        @NotNull(message = "L'id del dipendente è obbligatorio")
        Integer userId,
        @NotNull(message = "Il titolo è obbligatorio")
        String titolo,
        @NotNull(message = "La descrizione è obbligatoria")
        String descrizione,
        @NotNull(message = "La data è obbligatoria")
        Date date,
        @NotNull(message = "Il luoog è obbligatorio")
        String luogo,
        @NotNull(message = "I posti disponibili da mettere sono obbligatori")
        Integer postiDisponibili
) {
}
