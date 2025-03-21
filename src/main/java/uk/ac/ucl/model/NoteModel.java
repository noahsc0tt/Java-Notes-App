package uk.ac.ucl.model;

import uk.ac.ucl.exceptions.JSONFileNotFoundException;
import uk.ac.ucl.exceptions.JSONParseException;
import uk.ac.ucl.util.JSONHandler;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;

// This class is responsible for creating, retrieving, updating, and deleting notes

class NoteModel
{
    private final LinkedHashMap<LocalDateTime, NoteRecord> noteData;
    
    NoteModel()
    {
        LinkedHashMap<LocalDateTime, NoteRecord> tempData;
        try { tempData = JSONHandler.readJSON(); }
        catch (JSONFileNotFoundException | JSONParseException e)
        {
            e.printStackTrace();
            tempData = new LinkedHashMap<>();
        }
        noteData = tempData;
    }
    
    LinkedHashMap<LocalDateTime, NoteRecord> getNoteMap() { return new LinkedHashMap<>(noteData); }
    NoteRecord getNoteRecord(LocalDateTime key) { return noteData.get(key); }
    
    void addNote(String name, String body)
    {
        NoteRecord newNote = new NoteRecord(name, body);
        noteData.put(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), newNote);
    }
    
    void updateNote(LocalDateTime key, String name, String body)
    {
        NoteRecord newNote = new NoteRecord(name, body);
        noteData.put(key, newNote);
    }
    
    void deleteNote(LocalDateTime key) { noteData.remove(key); }
    
}
