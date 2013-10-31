
package wad.timetables.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wad.timetables.domain.BusStop;
import wad.timetables.domain.Departure;
import wad.timetables.domain.MyBusStopList;

@Service
public class RESTStopService {
    private String stopUrl = "http://api.reittiopas.fi/hsl/prod/?request=stop&user=locust&pass=express&code=";
    private RestTemplate restTemplate;
    
    @PostConstruct
    private void init() {
        restTemplate = new RestTemplate();    
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        
        List<MediaType> mediaList = new ArrayList<MediaType>();
        mediaList.add(MediaType.TEXT_PLAIN);
        mediaList.add(MediaType.APPLICATION_JSON);
        
        converter.setSupportedMediaTypes(mediaList);
        restTemplate.getMessageConverters().add(converter);
    }
    
    public List<BusStop> searchStops(String search) {
        List<BusStop> stops = restTemplate.getForObject(stopUrl + search, MyBusStopList.class);
        return stops;
    }
    
    public BusStop getStop(Long stopCode){
        List<BusStop> stops = restTemplate.getForObject(stopUrl + stopCode, MyBusStopList.class);
        if (!stops.isEmpty()) {
            return stops.get(0);
        }
        return null;
    }
    
    public List<Departure> getDepartures(BusStop busStop){
        busStop.parseAllDepartures();
        return busStop.getDepartures();
    }
}
