package rockeatseat.com.passin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rockeatseat.com.passin.domain.attendee.Attendee;
import rockeatseat.com.passin.domain.attendee.exceptions.AttendeeAlreadyExistExcepition;
import rockeatseat.com.passin.domain.checkin.CheckIn;
import rockeatseat.com.passin.domain.envent.exceptions.AttendeeNotFoundException;
import rockeatseat.com.passin.dto.attendee.AttendeeBadgeResponseDTO;
import rockeatseat.com.passin.dto.attendee.AttendeeDetails;
import rockeatseat.com.passin.dto.attendee.AttendeesListResponseDTO;
import rockeatseat.com.passin.dto.attendee.BadgeDTO;
import rockeatseat.com.passin.repositories.AttendeeRepository;
import rockeatseat.com.passin.repositories.CheckinRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckinRepository checkinRepository;
    public  List<Attendee > getAllAttendeeFromEvent(String eventId){

        return this.attendeeRepository.findByEventId(eventId);
    }
    public AttendeesListResponseDTO getEventsAttendee(String eventId){
        List<Attendee> attendeeList = this.getAllAttendeeFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<CheckIn> checkIn = this.checkinRepository.findByAttendeeId(attendee.getId());

            LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);
            return  new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
        }).toList();
        return new AttendeesListResponseDTO(attendeeDetailsList);
    }

    public Attendee registerAttendee(Attendee newAttendee){
        this.attendeeRepository.save(newAttendee);
        return newAttendee;

    }

    public void verifyAttendeeSubscription(String email, String eventId){
        Optional<Attendee> isAttendeeRegistered = this.attendeeRepository.findByEventIdAndEmail(eventId, email);
        if(isAttendeeRegistered.isPresent()) throw  new AttendeeAlreadyExistExcepition("Attendee is already registered");
    }

    public AttendeeBadgeResponseDTO getAttendeeBadge(String attendeeId){
        Attendee attendee =  this. attendeeRepository.findById(attendeeId).orElseThrow(() -> new AttendeeNotFoundException("attendee not found with id" + attendeeId));
        BadgeDTO badgeDTO = new BadgeDTO(attendee.getName(), attendee.getEmail(), "", attendee.getEvent().getId());
        return new AttendeeBadgeResponseDTO(badgeDTO);

    }

}
