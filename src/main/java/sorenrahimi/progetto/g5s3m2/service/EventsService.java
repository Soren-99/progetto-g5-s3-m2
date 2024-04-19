package sorenrahimi.progetto.g5s3m2.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sorenrahimi.progetto.g5s3m2.entities.User;
import sorenrahimi.progetto.g5s3m2.entities.Event;
import sorenrahimi.progetto.g5s3m2.exceptions.NotFoundException;
import sorenrahimi.progetto.g5s3m2.payload.events.NewEventPayload;
import sorenrahimi.progetto.g5s3m2.repositories.EventsRepository;

import java.util.List;
@Service
public class EventsService {
    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private UsersService usersService;


    public Event save(NewEventPayload body) {
        User user = usersService.findById(body.userId());
        Event newEvent = new Event ();
        newEvent.setTitolo(body.titolo());
        newEvent.setDescrizione(body.descrizione());
        newEvent.setDate(body.date());
        newEvent.setLuogo(body.luogo());
        newEvent.setPostiDisponibili(body.postiDisponibili());
        return eventsRepository.save(newEvent);
    }


    public List<Event> getDispositivi() {
        return eventsRepository.findAll();
    }

    public Event findById(int id) {
        return eventsRepository.findById(id).orElseThrow(()-> new
                NotFoundException(id));
    }

    public void findByIdAndDelete(int id){
        Event found = this.findById(id);
        eventsRepository.delete(found);
    }

    public Event findByIdAndUpdate(int id, NewEventPayload body){
        Event found = this.findById(id);

        found.setTitolo(body.titolo());
        found.setDescrizione(body.descrizione());
        found.setDate(body.date());
        found.setLuogo(body.luogo());
        found.setPostiDisponibili(body.postiDisponibili());
        if (found.getUser().getId() != body.userId()) {
            User newUser =
                    usersService.findById(body.userId());
            found.setUser(newUser);
        }
        return eventsRepository.save(found);
    }
    public List<Event> findByUser(int userId){
        User user = usersService.findById(userId);
        return eventsRepository.findByUser(user);
    }
}

