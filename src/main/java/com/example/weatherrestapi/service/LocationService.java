package com.example.weatherrestapi.service;

import com.example.weatherrestapi.entity.Location;
import com.example.weatherrestapi.exception.LocationNotFoundException;
import com.example.weatherrestapi.repo.LocationRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private LocationRepo locationRepo;

    public LocationService(LocationRepo locationRepo) {
        super();
        this.locationRepo = locationRepo;
    }

    public Location add(Location location){
        return locationRepo.save(location);
    }
    public Location findByCode(String code){
        return locationRepo.findUnTrashedByCode(code);
    }
    public List<Location> listLocation(){
        return locationRepo.findUnTrashed();
    }

    public Location updateLocation (Location locationRequest) throws LocationNotFoundException {
        String code = locationRequest.getCode();

        Location locationInDb = findByCode(code);
        if(locationInDb == null){
            throw new LocationNotFoundException("No location found with given code" + code);
        }
        locationInDb.setCityName(locationRequest.getCityName());
        locationInDb.setCountryCode(locationRequest.getCountryCode());
        locationInDb.setRegionName(locationRequest.getRegionName());
        locationInDb.setCountryName(locationRequest.getCountryName());
        locationInDb.setEnabled(locationRequest.isEnabled());

        return  locationRepo.save(locationInDb);
    }
    public void deleteLocation (String code) throws LocationNotFoundException {
        Location locationInDb = findByCode(code);
        if(locationInDb == null){
            throw new LocationNotFoundException("No location found with given code" + code);
        }
        locationRepo.deleteLocation(code);
    }
}
