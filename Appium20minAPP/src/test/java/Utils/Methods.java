package Utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

@Slf4j
public class Methods {
    public static boolean checkIfElementExists(WebElement element) {
        boolean exist;
        try{
            exist = element.isDisplayed();
        } catch (NoSuchElementException e) {
            exist = false;
        }
        return exist;
    }

    public static void clickButton(WebElement element) {
        log.info("Clicking the button ....");

        try {
            element.click();
        } catch (TimeoutException ex) {
            log.info("Timeout clicking the button: " + ex.getClass().getSimpleName());
        }
    }

    public static String acortarTextoA5Palabras(String texto) {
        String[] palabras = texto.split(" "); // Divide el String en palabras usando espacios como delimitador

        if (palabras.length <= 5) {
            return texto; // El número de palabras en el texto original es igual o menor a 5
        } else {
            StringBuilder resultado = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                resultado.append(palabras[i]).append(" ");
            }
            resultado.append("..."); // Agrega "..." al final para indicar que se ha acortado el texto
            return resultado.toString().trim(); // Convierte el StringBuilder a String y elimina los espacios finales
        }
    }

}
