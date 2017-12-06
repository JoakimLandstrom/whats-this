package se.jola.whatsthis.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.jola.whatsthis.models.LocationRequest;
import se.jola.whatsthis.models.LocationResponse;
import se.jola.whatsthis.services.LocationService;

import java.util.List;

@RestController
@RequestMapping(value = "/locations", produces = "Application/json")
@CrossOrigin(origins = "*")
public final class LocationResource {

    @Autowired
    private LocationService service;

    @GetMapping
    public List<LocationResponse> getClosestPois(LocationRequest locationRequest) {
        return service.getLocations(locationRequest);
    }

}
