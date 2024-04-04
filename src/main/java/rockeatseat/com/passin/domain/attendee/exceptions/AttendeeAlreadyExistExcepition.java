package rockeatseat.com.passin.domain.attendee.exceptions;

public class AttendeeAlreadyExistExcepition extends RuntimeException {
    public  AttendeeAlreadyExistExcepition(String message){
        super(message);
    }
}
