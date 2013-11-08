package wad.timetables.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStop {

    @JsonProperty("code")
    private Long code;
    @JsonProperty("name_fi")
    private String name;
    @JsonProperty("departures")
    private List<Departure> departures;
    @JsonProperty("wgs_coords")
    private String wgs_coords;
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    public String getWgs_coords() {
        return wgs_coords;
    }

    public void setWgs_coords(String wgs_coords) {
        this.wgs_coords = wgs_coords;
    }
    
    public void setLatitudeAndLongitude() { 
        this.latitude = Double.parseDouble(this.wgs_coords.split(",")[1]);
        this.longitude = Double.parseDouble(this.wgs_coords.split(",")[0]);
    }
    
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
