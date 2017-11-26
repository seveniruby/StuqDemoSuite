package unit;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestDemo3 {
    @Test
    public void testX(){
        System.out.println("console log");
        assertThat("xxx", 1, is(2));
    }
}
