package io.pivotal.pal.tracker.repository;

import io.pivotal.pal.tracker.entity.TimeEntry;

import java.util.ArrayList;
import java.util.List;


public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private final long projectId = 123L;
    private final long userId = 456L;
    private List<TimeEntry> myEntries = new ArrayList<>();
    private long currentId = 1;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(currentId);
        currentId += 1;
        myEntries.add(timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return myEntries.stream().filter(timeEntry -> timeEntry.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<TimeEntry> list() {
        return myEntries;

    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry existingObject = find(id);

        if (existingObject == null) {
            return null;

        }
        // timeEntry.setId(id);
        existingObject.setUserId(timeEntry.getUserId());
        existingObject.setProjectId(timeEntry.getProjectId());
        existingObject.setDate(timeEntry.getDate());
        existingObject.setHours(timeEntry.getHours());
        return existingObject;
    }

    @Override
    public void delete(Long id) {
        myEntries.removeIf(timeEntry -> timeEntry.getId() == id);
    }

}
