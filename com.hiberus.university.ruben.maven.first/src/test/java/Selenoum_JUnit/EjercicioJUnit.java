package Selenoum_JUnit;

import org.junit.*;

public class EjercicioJUnit {

    @BeforeClass
    public static void setUpClass() {
        System.out.println("BEFORECLASS");
    }

    @Before
    public void setUp() {
        System.out.println("BEFORE");
    }
/*
    @Test
    public void testAssertFalse() {
        Assert.assertFalse(false);
    }
 */
/*
    @Test
    public void testSumar() {
        Assert.assertEquals("El resultado no es el correcto", (1 + 2), 2);
    }

 */

/*
    @Test
    public void testAssertArrayEquals() {
        String []nombresEsperados = { "java" , "junit" , "jboss" };
        String []nombresActuales = { "java" , "junit" , "jboss" };

        Assert.assertArrayEquals("Fallo - No son los mismos arrays", nombresEsperados, nombresActuales);
    }

 */
/*
    @Test
    public void test01() {
        System.out.println("TEST 01");
    }

    @Test
    public void test02() {
        System.out.println("TEST 02");
    }

    @Test
    public void test03() {
        System.out.println("TEST 03");
    }
 */


    @After
    public void tearDown() {
        System.out.println("AFTER");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("AFTERCLASS");
    }
}
