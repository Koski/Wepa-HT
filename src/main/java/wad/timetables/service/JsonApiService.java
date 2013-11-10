
package wad.timetables.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.timetables.domain.BusStop;
import wad.timetables.domain.Departure;
import wad.timetables.domain.User;

@Service
public class JsonApiService {
    
    @Autowired
    private UserService userService;
    @Autowired
    private RESTStopService stopService;
    
    
    public List<BusStop> listFavStops(User user) {
        return userService.getCurrentStopInfo(user);
    }
    
    
    public List<Departure> getDeparturesOfStop(BusStop stop) {
       return stopService.getDepartures(stop);
    }
}
