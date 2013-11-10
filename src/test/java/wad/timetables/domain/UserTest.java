/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.timetables.domain;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antti
 */
public class UserTest {
    
   private User user;
   
    @Before
    public void setUp() {
        user = new User();
    }
    
    @Test
    public void listOfFavStopsGetsInitialized() {
        assertNotNull(user.getCodesOfFavStops());
    }
}
