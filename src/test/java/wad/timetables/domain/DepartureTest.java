
package wad.timetables.domain;
import javax.validation.constraints.AssertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class DepartureTest {
    
   private Departure departure;
   private String codeOf543 = "2543  1";
   private String codeOf143a= "2143A 1";
   private String codeOf12 = "2012  1";
   
    @Before
    public void setUp() {
        departure = new Departure();
    }
    
    @Test
    public void lineCodesGetParsedToActualBusLines() {
        departure.parseLineCode(codeOf12);
        assertTrue(departure.getLineCode().equals("12 "));
        
        departure.parseLineCode(codeOf543);
        assertTrue(departure.getLineCode().equals("543 "));
        
        departure.parseLineCode(codeOf143a);
        assertTrue(departure.getLineCode().equals("143A"));
    }
    
    @Test
    public void passTimesGetParsedToCorrectFormat() {
        departure.setPassingTime("2034");
        departure.parsePassingTime("2034");
        assertTrue(departure.getPassingTime().equals("20:34"));
        
        departure.setPassingTime("1045");
        departure.parsePassingTime("1045");
        assertTrue(departure.getPassingTime().equals("10:45"));
    }
}
