package rockeatseat.com.passin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rockeatseat.com.passin.dto.attendee.AttendeeBadgeResponseDTO;
import rockeatseat.com.passin.services.AttendeeService;
import rockeatseat.com.passin.services.CheckInService;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping("/{attendeeId}/badge")
    public ResponseEntity<AttendeeBadgeResponseDTO> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder){
        AttendeeBadgeResponseDTO response = this.attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/{attendeeId}/check-in")
    public ResponseEntity registerCheckIn(@PathVariable String attendId, UriComponentsBuilder uriComponentsBuilder){
        this.attendeeService.checkAttendee(attendId);
        var uri = uriComponentsBuilder.path("attendees/{attendId}/badge").buildAndExpand(attendId).toUri();
        return ResponseEntity.created(uri).build();
    }
}
