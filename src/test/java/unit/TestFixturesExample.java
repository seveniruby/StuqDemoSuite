package unit;

import java.io.Closeable;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestFixturesExample {
    @BeforeClass
    public static void setUpClass() {
        System.out.println("@BeforeClass setUpClass");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("@AfterClass tearDownClass");
    }

    private void println(String string) {
        System.out.println(string);
    }

    @Before
    public void setUp() {
        this.println("@Before setUp");
    }

    @After
    public void tearDown() throws IOException {
        this.println("@After tearDown");
    }

    @Test
    public void test1() {
        this.println("@Test test1()");
        assertThat("error 2!=1", 2, is(1));
    }

    @Test
    public void test2() {
        this.println("@Test test2()");
    }
}