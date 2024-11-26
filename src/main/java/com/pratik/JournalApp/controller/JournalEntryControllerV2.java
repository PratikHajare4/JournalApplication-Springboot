package com.pratik.JournalApp.controller;

import com.pratik.JournalApp.entity.JournalEntry;
import com.pratik.JournalApp.entity.User;
import com.pratik.JournalApp.service.JournalEntryService;
import com.pratik.JournalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<JournalEntry> all =user.getJournalEntries();
        if(all != null){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){

        try {

            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry  , HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJouranalEntryById(@PathVariable ObjectId myId){
      //  return Optional.ofNullable(journalEntryService.findById(myId).orElse(null));

        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get()  , HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);


    }


    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJouranalEntryById(@PathVariable ObjectId myId, @PathVariable String userName){
        journalEntryService.deleteById(myId, userName);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{userName}/{id}")
    public  ResponseEntity<?> updateJournalById(
            @PathVariable ObjectId id,
            @RequestBody JournalEntry newEntry,
            @PathVariable String userName){
            JournalEntry old = journalEntryService.findById(id).orElse(null);
            if(old != null){
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());

                journalEntryService.saveEntry(old);

                return new ResponseEntity<>(old , HttpStatus.OK);
            }
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}