import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPages {

    WebDriver driver;

    CheckoutPages(WebDriver driver){
        this.driver=driver;
    }

    public WebElement ContinueShoppingButton(){
        return driver.findElement(By.id("continue-shopping"));
    }
    public WebElement CheckOutButton(){
        return driver.findElement(By.name("checkout"));

    }
    public WebElement YourFirstNameField(){
        return driver.findElement(By.id("first-name"));
    }

    public WebElement YourLastNameField(){
        return driver.findElement(By.id("last-name"));
    }
    public WebElement YourPostalCodeField(){
        return driver.findElement(By.id("postal-code"));
    }
    public WebElement ContinueButton(){

       WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
       WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("continue")));
        return continueButton ;}
    public WebElement FinishButton(){
        return driver.findElement(By.id("finish"));
    }
    public WebElement BackHomeButton(){
        return driver.findElement(By.id("back-to-products"));
    }
    public void userInformation(String firstname,String lastname,String postalcode){
        YourFirstNameField().sendKeys(firstname);
        YourLastNameField().sendKeys(lastname);
        YourPostalCodeField().sendKeys(postalcode);
}

}
