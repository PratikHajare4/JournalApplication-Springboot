package com.pratik.JournalApp.service;


import com.pratik.JournalApp.entity.JournalEntry;
import com.pratik.JournalApp.entity.User;
import com.pratik.JournalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

   @Autowired
private JournalEntryRepository journalEntryRepository;

   @Autowired
   private UserService userService;


  @Transactional
  public void saveEntry(JournalEntry journalEntry, String userName){
      try {
          User user = userService.findByUserName(userName);
          JournalEntry saved =journalEntryRepository.save(journalEntry);
          user.getJournalEntries().add(saved);
          userService.saveEntry(user);

      } catch (Exception e){
          System.out.println(e);
          throw new RuntimeException("Any error occurred while saving the entry. ",e);
      }

   }

   public void saveEntry(JournalEntry journalEntry){

     journalEntryRepository.save(journalEntry);
   }

   public List<JournalEntry> getAll(){
      return journalEntryRepository.findAll();
   }

   public Optional<JournalEntry> findById(ObjectId id){
     return journalEntryRepository.findById(id);
   }

   public void deleteById(ObjectId id, String userName){
      User user = userService.findByUserName(userName);
      user.getJournalEntries().removeIf(x -> x.getId().equals(id));
      userService.saveEntry(user);
      journalEntryRepository.deleteById(id);
   }
}
//Controller---->Service---->Repository