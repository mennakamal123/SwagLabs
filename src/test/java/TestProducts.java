import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestProducts extends TestBase
{
    ProductsPage products;

@Test(priority = 1)
public void dropdownMenu() {
    products = new ProductsPage(driver);
    // Higher price to lower
    products.ProductsPageDropDowMenu("hilo");

    // lower price to higher
    products.ProductsPageDropDowMenu("lohi");

    // sorted from Z to A
    products.ProductsPageDropDowMenu("za");

    // sorted from A to Z
    products.ProductsPageDropDowMenu("az");



}
@Test(priority = 2)
public void sidebarLinks (){
    //About link
    products=new ProductsPage(driver);
    products.ClickOnSidebar().click();
    products.ClickOnAboutLink().click();
    String ExpectedUrl="https://saucelabs.com/";
    String ActualUrl= driver.getCurrentUrl();
    Assert.assertEquals(ActualUrl,ExpectedUrl);
    driver.navigate().to("https://www.saucedemo.com/inventory.html");


}
    @Test(priority = 3)
    public void followUsLinks() {
        ProductsPage products = new ProductsPage(driver);
        String originalTab = driver.getWindowHandle();
        // Twitter link
        products.twitterLink().click();
        switchToNewTab(originalTab);
        String twitterUrl = driver.getCurrentUrl();
        Assert.assertTrue(twitterUrl.contains("https://x.com/saucelabs"));
        driver.close();
        driver.switchTo().window(originalTab);

         // LinkedIn link
        products.linkedinLink().click();
        switchToNewTab(originalTab);
        String linkedinUrl = driver.getCurrentUrl();
        Assert.assertTrue(linkedinUrl.contains("linkedin"));
        driver.close();
        driver.switchTo().window(originalTab);

        // Facebook link
        products.facebookLink().click();
        switchToNewTab(originalTab);
        String facebookUrl = driver.getCurrentUrl();
        Assert.assertTrue(facebookUrl.contains("https://www.facebook.com/saucelabs"));
        driver.close();
        driver.switchTo().window(originalTab);
    }


    private void switchToNewTab(String originalTab) {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalTab)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }


 @Test(priority = 4)
    public void products(){
    products=new ProductsPage(driver);
    driver.navigate().to("https://www.saucedemo.com/inventory.html");
    products.AddProduct3ToCart().click();
    products.RemoveProduct3FromCart().click();
    products.AddProduct1ToCart().click();
    products.ClickOnProduct2().click();
    products.AddProduct2toCart().click();
    products.ClickOnCartIcon().click();
     //Assert product 3 is removed from cart
     try {
         driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs Bolt T-Shirt')]"));
         Assert.fail("Product is still present in the cart " + "Sauce Labs Bolt T-Shirt");
     } catch (NoSuchElementException e) {
         System.out.println("Product has been successfully removed from the cart.");
     }
    //Assert product 1 is added to cart
    String ActualResult=driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs Backpack')]")).getText();
    String ExpectedResult="Sauce Labs Backpack";
    Assert.assertTrue(ActualResult.contains(ExpectedResult),"Sauce Labs Backpack is not added to cart");
    //Assert product 2 is added to cart
    String actualResult=driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs Bike Light')]")).getText();
    String expectedResult="Sauce Labs Bike Light";
    Assert.assertTrue(actualResult.contains(expectedResult),"Sauce Labs Bike Light is not added to cart");

     products.ContinueShopingButton().click();
     Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html","User was expected to be redirected to the homepage but wasn't.");
     products.ClickOnCartIcon().click();
}
}
