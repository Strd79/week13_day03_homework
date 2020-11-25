package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getWhiskiesByParticularYearOrRegion(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "region", required = false) String region
    ) {
       if(year != null) {
           return new ResponseEntity(whiskyRepository.findWhiskiesByYear(year), HttpStatus.OK);
       }
       if(region != null) {
           return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryRegion(region), HttpStatus.OK);
       }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/distilleries")
    public ResponseEntity<List<Whisky>> getWhiskiesByDistilleryAndAge(
            @RequestParam(name = "distillery") String distillery,
            @RequestParam(name = "age") int age
            ) {
        return new ResponseEntity(whiskyRepository.findWhiskiesByDistilleryNameAndAge(distillery, age), HttpStatus.OK);
    }
}
