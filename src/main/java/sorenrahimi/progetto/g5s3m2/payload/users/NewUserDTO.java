package sorenrahimi.progetto.g5s3m2.payload.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(

        @NotEmpty(message = "Lo username è obbligatorio")
        @Size(min = 3, max = 30, message = "Nome deve avere" +
                "minimo 3 caratteri, massimo 30")
        String username,
        @NotEmpty(message = "Il nome è obbligatorio")
        @Size(min = 3, max = 30, message = "Nome deve avere" +
                "minimo 3 caratteri, massimo 30")
        String nome,
        @NotEmpty(message = "il cognome è obbligatorio")
        String cognome,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è valida")
        String email,
        @NotEmpty(message = "la password è obbligatorio")
        String password

) {
}