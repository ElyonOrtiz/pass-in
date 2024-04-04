package rockeatseat.com.passin.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rockeatseat.com.passin.domain.attendee.exceptions.AttendeeAlreadyExistExcepition;
import rockeatseat.com.passin.domain.checkin.exception.CheckInAlreadyExistsException;
import rockeatseat.com.passin.domain.envent.exceptions.AttendeeNotFoundException;
import rockeatseat.com.passin.domain.envent.exceptions.EventIsFullException;
import rockeatseat.com.passin.domain.envent.exceptions.EventNotFoundException;

@ControllerAdvice
public class ExceptionEntityHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity handleEventNotFound(EventNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity handleAttendeeNotFound(AttendeeNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(AttendeeAlreadyExistExcepition.class)
    public ResponseEntity handleAttendeeAlreadyExist(AttendeeAlreadyExistExcepition exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    @ExceptionHandler(CheckInAlreadyExistsException.class)
    public ResponseEntity handleCheckInAlreadyExists(CheckInAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    @ExceptionHandler(EventIsFullException.class)
    public ResponseEntity handleEventIsFull(EventIsFullException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
