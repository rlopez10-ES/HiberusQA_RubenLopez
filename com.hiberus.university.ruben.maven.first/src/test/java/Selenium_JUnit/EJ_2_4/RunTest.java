package Selenium_JUnit.EJ_2_4;

import Selenium_JUnit.Login;
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
