package wad.timetables.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;


//@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Departure {
    
    @JsonProperty("code")
    private String lineCode;
    @JsonProperty("time")
    private String passingTime;
    @JsonProperty("date")
    private String date;
    private int minutesUntillPassesStop;

    public Departure() {
    }
    
    public int getMinutesUntillPassesStop() {
        return minutesUntillPassesStop;
    }
    
    public void setMinutesUntillPassesStop(int minutesUntillPassesStop) {
        this.minutesUntillPassesStop = minutesUntillPassesStop;
    }
    
    public String getLineCode() {
        return lineCode;
    }
    
    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getPassingTime() {
        return passingTime;
    }
    
    public void setPassingTime(String passingTime) {
        this.passingTime = passingTime;
    }
    
    public void calculateMinutesUntillPass() {
        Date now = new Date();
        Date passtime = new Date();
        passtime.setHours(Integer.parseInt(passingTime.substring(0, 2)));
        passtime.setMinutes(Integer.parseInt(passingTime.substring(2)));
        this.minutesUntillPassesStop = (int)((passtime.getTime()/60000)-(now.getTime()/60000)); 
    }
    
    public void parsePassingTime(String passingTime) {
        String hours = passingTime.substring(0, 2);
        String minutes = passingTime.substring(2, 4);
        calculateMinutesUntillPass();
        this.passingTime = hours + ":" + minutes;
    }
    
    public void parseLineCode(String lineCode) {
        String parsed = lineCode.substring(1, 5);
        parsed = parsed.replaceFirst("^0+(?!$)", "");
        this.lineCode = parsed;
    }
}
