package wad.timetables.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
//import javax.persistence.Entity;

//@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStop {

    @JsonProperty("code")
    private Long code;
    @JsonProperty("name_fi")
    private String name;
    @JsonProperty("departures")
    private List<Departure> departures;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Departure> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Departure> departures) {
        this.departures = departures;
    }

    public void parseAllDepartures() {
        if (!departures.isEmpty()) {
            for (Departure departure : departures) {
                departure.parsePassingTime(departure.getPassingTime());
                departure.parseLineCode(departure.getLineCode());
            }
        }
        
    }
}
