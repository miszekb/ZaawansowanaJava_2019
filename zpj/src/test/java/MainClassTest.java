import org.junit.Test;

import static org.junit.Assert.*;

public class MainClassTest {

    @Test
    public void haloString() {
        MainClass mc = new MainClass();
        assertEquals(mc.haloString(),"halooo");
    }
}