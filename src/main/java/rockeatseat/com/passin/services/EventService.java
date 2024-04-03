package rockeatseat.com.passin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rockeatseat.com.passin.domain.attendee.Attendee;
import rockeatseat.com.passin.domain.envent.Event;
import rockeatseat.com.passin.domain.envent.exceptions.EventNotFoundException;
import rockeatseat.com.passin.dto.event.EventIdDTO;
import rockeatseat.com.passin.dto.event.EventRequestDTO;
import rockeatseat.com.passin.dto.event.EventResponseDTO;
import rockeatseat.com.passin.repositories.AttendeeRepository;
import rockeatseat.com.passin.repositories.EventRepository;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;

    public EventResponseDTO getEventDetail(String eventId){
        Event event = this.eventRepository.findById(eventId).orElseThrow(()-> new EventNotFoundException("Event not found with ID:" + eventId));
        List<Attendee> attendeeList = this.attendeeService.getAllAttendeeFromEvent(eventId);
        return new EventResponseDTO(event, attendeeList.size());
    }

    public EventIdDTO createEvent(EventRequestDTO eventDTO){
        Event newEvent = new Event();
        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(eventDTO.title()));

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text){
         String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
         return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                 .replaceAll("[^\\w\\s]","")
                 .replaceAll("[\\s+]", "-")
                 .toLowerCase();
    }
}