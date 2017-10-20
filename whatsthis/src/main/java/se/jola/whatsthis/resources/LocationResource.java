package se.jola.whatsthis.resources;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jola.whatsthis.models.LocationRequest;
import se.jola.whatsthis.services.LocationService;

@RestController
@RequestMapping(value = "/locations", produces = "Application/json")
@CrossOrigin(origins = "*")
public final class LocationResource {

    @Autowired
    private LocationService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getClosestPois(LocationRequest locationRequest) {
        return ResponseEntity.ok(new Gson().toJson(service.getLocations(locationRequest)));
    }

}
