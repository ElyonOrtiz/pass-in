package rockeatseat.com.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rockeatseat.com.passin.domain.checkin.CheckIn;

import java.util.Optional;

public interface CheckinRepository extends JpaRepository<CheckIn, Integer> {
    Optional<CheckIn> findByAttendeeId(String attendeeId);
}
