package com.pratik.JournalApp.controller;

import com.pratik.JournalApp.api.response.WeatherResponse;
import com.pratik.JournalApp.entity.User;
import com.pratik.JournalApp.repository.UserRepository;
import com.pratik.JournalApp.service.UserService;
import com.pratik.JournalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

//    @GetMapping
//    public List<User> getAllUsers(){
//       return userService.getAll();
//    }



    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
       Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
       String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();

        WeatherResponse weatherResponse = weatherService.getWeather("Pune");

        String greetngs = "";

        if(weatherResponse != null){
            greetngs = ", Weather feels like "+ weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi "+authentication.getName()+ greetngs ,HttpStatus.OK);
    }


}
