package rockeatseat.com.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rockeatseat.com.passin.domain.envent.Event;

public interface EventRepository extends JpaRepository<Event, String> {

}
