package sorenrahimi.progetto.g5s3m2.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sorenrahimi.progetto.g5s3m2.entities.User;
import sorenrahimi.progetto.g5s3m2.entities.Event;

import java.util.List;


@Repository
public interface EventsRepository extends JpaRepository<Event, Integer> {
    List<Event> findByUser(User user);
}
