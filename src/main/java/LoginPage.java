import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
WebDriver driver;

LoginPage(WebDriver driver){
    this.driver=driver;
}

    public WebElement UsernameEle(){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement userNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
      // return driver.findElement(By.id("user-name"));
        return  userNameField;
    }
    public WebElement PasswordEle(){


        return driver.findElement(By.id("password"));
    }
    public WebElement LoginButtonEle(){


        return driver.findElement(By.name("login-button"));
    }

    public void loginSteps(String username,String password){
         UsernameEle().sendKeys(username);
          PasswordEle().sendKeys(password);
          LoginButtonEle().click();

    }

}
