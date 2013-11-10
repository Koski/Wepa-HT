
package wad.timetables.domain;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class BusStopTest {
    
    private BusStop stop;
    private String konemiesCoords = "24.82324,60.18629";
    private String iivisniemiCoords = "24.68924,60.15000";
    
    @Before
    public void setUp() {
        stop = new BusStop();
    }
    
    @Test
    public void latitudeAndLongitudeGetsParsedCorrectly() {
        stop.setWgs_coords(konemiesCoords);
        stop.setLatitudeAndLongitude();
        assertEquals(stop.getLatitude(), 60.18629, 0.01);
        assertEquals(stop.getLongitude(), 24.82324, 0.01);
        
        stop.setWgs_coords(iivisniemiCoords);
        stop.setLatitudeAndLongitude();
        assertEquals(stop.getLatitude(), 60.15000, 0.01);
        assertEquals(stop.getLongitude(), 24.68924, 0.01);
    }
}
