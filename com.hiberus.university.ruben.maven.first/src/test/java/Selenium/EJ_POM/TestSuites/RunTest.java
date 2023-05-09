package Selenium.EJ_POM.TestSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Login.class,
        Inventario.class,
        Carrito.class,
        Checkout.class
})

public class RunTest {

}
