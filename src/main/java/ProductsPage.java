import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class ProductsPage {
    WebDriver driver;
   WebDriverWait wait;
    ProductsPage(WebDriver driver){
        this.driver=driver;
    }

    public Select sortingProducts(){
        WebElement dropdownElement = driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]"));
        Select dropdown = new Select(dropdownElement);

        return dropdown;
    }



    public WebElement twitterLink(){
            WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement twitter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),\"Twitter\")]")));
            return twitter;

        }
        public WebElement facebookLink(){
            WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement facebook = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),\"Facebook\")]")));
            return facebook;

        }
       public WebElement linkedinLink(){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Linkedin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),\"LinkedIn\")]")));
        return Linkedin;

    }
        public WebElement AddProduct1ToCart(){
            return driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        }


        public WebElement ClickOnProduct2(){
            WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement productClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Sauce Labs Bike Light')]")));
            return productClick;
             }
        public WebElement AddProduct2toCart(){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addProduct2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart")));
        return addProduct2;

    }
        public WebElement AddProduct3ToCart(){
            return driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        }
        public WebElement RemoveProduct3FromCart(){
            return driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt"));
        }


       public WebElement ClickOnSidebar() {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         WebElement sidebar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-burger-menu-btn")));
         return sidebar;}
       public WebElement ClickOnLogoutLink(){
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
          WebElement LogoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));

          return LogoutLink;
}
       public WebElement ClickOnAboutLink(){
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
          WebElement AboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("about_sidebar_link")));

          return AboutLink;
       }
        public WebElement ClickOnCartIcon(){
            return driver.findElement(By.className("shopping_cart_link"));
        }

        public WebElement ContinueShopingButton(){
            return driver.findElement(By.id("continue-shopping"));
        }
       public void ProductsPageDropDowMenu(String value) {

           sortingProducts().selectByValue(value);
}

}
