package io.pivotal.pal.tracker.controllers;

import io.pivotal.pal.tracker.entity.TimeEntry;
import io.pivotal.pal.tracker.repository.TimeEntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository repo;

    public TimeEntryController(TimeEntryRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        TimeEntry createdEntity = repo.create(timeEntry);
        HttpStatus httpStatus = createdEntity != null ? HttpStatus.CREATED : HttpStatus.I_AM_A_TEAPOT;

        return new ResponseEntity(createdEntity, httpStatus);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = repo.find(id);

        HttpStatus httpStatus = timeEntry != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity(timeEntry, httpStatus);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok(repo.list());

    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {

        TimeEntry update = repo.update(id, timeEntry);

        HttpStatus httpStatus = update != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity(update, httpStatus);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        repo.delete(id);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }


}
