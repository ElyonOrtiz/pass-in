package rockeatseat.com.passin.domain.envent.exceptions;

public class EventIsFullException extends RuntimeException {
    public EventIsFullException(String message){
        super (message);
    }
}
