package de.deepak.doit.controller;

import de.deepak.doit.model.Note;
import de.deepak.doit.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by deepak on 14.12.15.
 */
@RestController
@RequestMapping("/note")
public class NotesController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NoteRepository noteRepo;

    @RequestMapping(method = RequestMethod.GET)
    public List<Note> getAllNotes(){
        System.out.println("Changed to test image tagging");
        return noteRepo.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Note addNote(@RequestParam(name = "note") String note){
        Note noteVO = new Note();
        noteVO.setNote(note);
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://data.sparkfun.com/streams/dZ4EVmE8yGCRGx5XRX1W.json",
                String.class);
        return noteRepo.saveAndFlush(noteVO);
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Note getNote(@PathVariable Integer id){
        return noteRepo.findOne(id);
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    public Note updateNote(@RequestParam(name = "note") String note, @PathVariable Integer id){
        Note noteVO = new Note();
        noteVO.setId(id);
        noteVO.setNote(note);
        return noteRepo.saveAndFlush(noteVO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteNote(@PathVariable Integer id){
        noteRepo.delete(id);
    }

}
