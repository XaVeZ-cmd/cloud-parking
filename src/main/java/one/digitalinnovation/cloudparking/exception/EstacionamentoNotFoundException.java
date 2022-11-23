package one.digitalinnovation.cloudparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EstacionamentoNotFoundException extends RuntimeException {
    public EstacionamentoNotFoundException(String id) {
        super("Estacionamento não existe para o id: " + id);
    }
}
