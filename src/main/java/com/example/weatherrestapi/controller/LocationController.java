package com.example.weatherrestapi.controller;

import com.example.weatherrestapi.entity.Location;
import com.example.weatherrestapi.exception.LocationNotFoundException;
import com.example.weatherrestapi.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/locations")
public class LocationController {
    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<Location> addLocation(@RequestBody @Valid Location location){
        Location locationSaved = locationService.add(location);
        URI uri = URI.create("/v1/locations/" + location.getCode());

        return ResponseEntity.created(uri).body(locationSaved);
    }
    @GetMapping
    public ResponseEntity<List<Location>> listLocation(){
        List<Location> locationList = locationService.listLocation();
        if(locationList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(locationList);
        }
    }
    @GetMapping("/{code}")
    public ResponseEntity<Location> listLocation(@PathVariable("code") String code){
        Location location = locationService.findByCode(code);
        if(location == null){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(location);
        }
    }

    @PutMapping()
    public ResponseEntity<Location> listLocation(@RequestBody @Valid Location location){
        try {
            locationService.updateLocation(location);

            return ResponseEntity.ok(location);
        } catch (LocationNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{code}")
    public ResponseEntity<?> deleteLocation(@PathVariable("code") String code){
        try {
            locationService.deleteLocation(code);
            return ResponseEntity.ok().build();
        } catch (LocationNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
