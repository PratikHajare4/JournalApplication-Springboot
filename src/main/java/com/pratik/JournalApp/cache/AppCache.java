package com.pratik.JournalApp.cache;

import com.pratik.JournalApp.entity.ConfigJournalAppEntity;
import com.pratik.JournalApp.repository.ConfigJouranlAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ConfigJouranlAppRepository configJouranlAppRepository;

    public Map<String, String> APP_CACHE ;

    @PostConstruct
    public void init(){

        APP_CACHE = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJouranlAppRepository.findAll();

        for( ConfigJournalAppEntity configJournalAppEntity: all){
            APP_CACHE.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }

    }
}
