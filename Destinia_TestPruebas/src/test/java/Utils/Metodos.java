package Utils;

import org.openqa.selenium.WebElement;

import java.util.List;

public class Metodos {

    public static void setAddRemove(int numActual, int num, WebElement deleteButton, WebElement addButton){
        if(numActual < num){
            for(int i = numActual; i < num; i++) {
                addButton.click();
            }
        } else if (numActual > num) {
            for(int i = numActual; i > num; i--) {
                deleteButton.click();
            }
        } else {
            System.out.println("You have already add " + num);
        }
    }

    public static int countAdultsChildren(int numActual, List<WebElement> elementsList){
        for (WebElement element : elementsList) {
            numActual += Integer.parseInt(element.getAttribute("value"));
        }
        return numActual;
    }
}
