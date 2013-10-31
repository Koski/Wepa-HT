package wad;

import org.junit.Test;
import static org.junit.Assert.*;

public class SampleTest {

    @Test
    public void sample() {
        int result = 1 + 1;
        assertNotEquals("1+1 should not be three", 3, result);
    }
}
