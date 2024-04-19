package sorenrahimi.progetto.g5s3m2.payload.errors;

import java.time.LocalDateTime;

public record ErrorsPayload(
        String message,
        LocalDateTime timestamp) { }
