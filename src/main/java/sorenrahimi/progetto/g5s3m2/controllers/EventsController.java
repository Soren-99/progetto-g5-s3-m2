package sorenrahimi.progetto.g5s3m2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sorenrahimi.progetto.g5s3m2.entities.Event;
import sorenrahimi.progetto.g5s3m2.exceptions.BadRequestException;
import sorenrahimi.progetto.g5s3m2.payload.events.NewEventPayload;
import sorenrahimi.progetto.g5s3m2.service.EventsService;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventsController {
    @Autowired
    EventsService eventsService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    @ResponseStatus(HttpStatus.CREATED)
    public Event saveEvento(@RequestBody @Validated NewEventPayload body,
                                       BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return eventsService.save(body);
    }

    @GetMapping("")
    public List<Event> getEventi(@RequestParam(required = false)
                                            Integer userId) {
        if (userId != null) return eventsService.findByUser(userId);
        else return eventsService.getDispositivi();
    }

    @GetMapping("/{eventoId}")
    public  Event findById(@PathVariable int eventId){
        return eventsService.findById(eventId);
    }

    @PutMapping("/{eventoId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    public Event findAndUpdate(@PathVariable int eventId,
                                     @RequestBody NewEventPayload body){
        return eventsService.findByIdAndUpdate(eventId, body);
    }

    @DeleteMapping("/{eventoId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int eventId){
        eventsService.findByIdAndDelete(eventId);
    }
}
