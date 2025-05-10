package zti.gymappspringbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestGymAppException extends RuntimeException {
    public BadRequestGymAppException(String message) {
        super(message);
    }

    public BadRequestGymAppException() {
        super("Bad request");
    }
}
