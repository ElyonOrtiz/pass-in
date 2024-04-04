package rockeatseat.com.passin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rockeatseat.com.passin.domain.attendee.Attendee;
import rockeatseat.com.passin.domain.checkin.CheckIn;
import rockeatseat.com.passin.domain.checkin.exception.CheckInAlreadyExistsException;
import rockeatseat.com.passin.domain.envent.exceptions.AttendeeNotFoundException;
import rockeatseat.com.passin.repositories.CheckinRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckInService {
    private final CheckinRepository checkinRepository;

    public void RegisterCheckIn(Attendee attendee){
        this.verifyCheckInExists(attendee.getId());
        CheckIn newCheckIn = new CheckIn();
        newCheckIn.setAttendee(attendee);
        newCheckIn.setCreatedAt(LocalDateTime.now());
        this.checkinRepository.save(newCheckIn);

    }

    private void verifyCheckInExists(String attendeeId){
        Optional<CheckIn> isCheckIn= this.getCheckIn(attendeeId);
        if(isCheckIn.isPresent()) throw new CheckInAlreadyExistsException("Attendee already checked in");
    }

    public Optional<CheckIn> getCheckIn(String attendeeId){
        return this.checkinRepository.findByAttendeeId(attendeeId);
    }

}
