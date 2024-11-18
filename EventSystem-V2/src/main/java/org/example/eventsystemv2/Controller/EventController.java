package org.example.eventsystemv2.Controller;


import jakarta.validation.Valid;
import lombok.Data;
import org.example.eventsystemv2.ApiResponse.ApiResponse;
import org.example.eventsystemv2.Model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v2/events-system")

public class EventController {


    ArrayList<Event> events = new ArrayList<>();


    @GetMapping("/display")
    public ResponseEntity displayEvents() {

        return ResponseEntity.status(200).body(events);
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors errors) {

        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }

        events.add(event);

        return ResponseEntity.status(200).body(new ApiResponse("Event added successfully!"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index, @RequestBody @Valid Event event, Errors errors) {

        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }

        events.set(index, event);
        return ResponseEntity.status(200).body(new ApiResponse("Event updated successfully!"));

    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index) {

        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Event deleted successfully!"));

    }

    //End CRUD endpoints


    @PutMapping("/change-capacity/{index}/{capacity}")
    public ResponseEntity changeCapacity(@PathVariable int index, @PathVariable int capacity) {

        for (int i = 0; i < events.size(); i++) {
            if (index == i)
                events.get(i).setCapacity(capacity);
        }
        return ResponseEntity.status(200).body(new ApiResponse("Capacity for this event changed successfully!"));
    }


    @GetMapping("/search-byID/{id}")
    public ResponseEntity searchByID(@PathVariable String id) {

        //No need for new array, the search is by unique value--ID--
        for (Event event : events) {
            if (event.getID().equals(id))
                return ResponseEntity.status(200).body(event);
        }

        return ResponseEntity.status(400).body(new ApiResponse("The event with this ID not found!"));
    }


} //End controller
