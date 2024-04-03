package rockeatseat.com.passin.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import rockeatseat.com.passin.domain.envent.exceptions.EventNotFoundException;

@ControllerAdvice
public class ExceptionEntityHandler {
    public ResponseEntity handleEventNotFound(EventNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
}
