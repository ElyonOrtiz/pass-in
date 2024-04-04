package rockeatseat.com.passin.domain.envent.exceptions;

public class AttendeeNotFoundException extends RuntimeException {
    public AttendeeNotFoundException (String message){
        super(message);
    }
}
