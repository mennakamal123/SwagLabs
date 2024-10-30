
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCheckout extends TestBase{
    CheckoutPages checkout;

    ProductsPage products;
    @DataProvider(name = "UserInformationInputs")
    public Object[][] UserInformationInputs() {
        return new Object[][]{
                {"", "Hassan","1234"},
                {"Menna","","1234"},
                {"Menna", "Hasan",""},
                {"men_a", "Hasan","1234"},
                {"Menna", "Hasa&","1234"},
                {"Menna", "Hasan","123%"},
                {"Menna", "Hasan","1234"},
        };
    }
    @Test(dataProvider = "UserInformationInputs", priority = 1)

    public void UserInformationPage(String firstname,String lastname,String postalcode){
        driver.navigate().to("https://www.saucedemo.com/cart.html");
        checkout=new CheckoutPages(driver);
        //Assert on checkout button
        checkout.CheckOutButton().click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html", "URL did not match User information page.");
        //Assert on user information
        checkout.userInformation(firstname,lastname,postalcode);
        if (firstname.isEmpty()){
          String ActualFirstnameError=driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3/text()")).getText();
            Assert.assertTrue(ActualFirstnameError.contains("Error: First Name is required"), " First name is accepted empty");
        }
        else if (firstname.equals("Men_a")) {
            String ActualFirstnameError=driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3/text()")).getText();
            Assert.assertTrue(ActualFirstnameError.contains("Error: First Name can't contain special characters"), "Special characters are accepted");}
        else if (lastname.equals("Hasa&")) {
            String ActualFirstnameError=driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3/text()")).getText();
            Assert.assertTrue(ActualFirstnameError.contains("Error: Last Name can't contain special characters"), "Special characters are accepted");}

        else if (firstname.equals("Menna") && lastname.equals("Hassan") && postalcode.equals("1234")) {
            Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-two.html"," URL did not match Overview page. ");}



    }

@Test(priority = 2)
    public void OverviewPage(){
    checkout=new CheckoutPages(driver);
    checkout.ContinueButton().click();
    String ActualResult = driver.findElement(By.xpath("//span[contains(text(),'Checkout: Overview')]")).getText();
    String Expectedresult = "Checkout: Overview";
    Assert.assertTrue(ActualResult.contains(Expectedresult));
    String ActualTotalPrice = driver.findElement(By.className("summary_total_label")).getText();
    String ExpectedTotalPrice="43.18";
    Assert.assertTrue(ActualTotalPrice.contains(ExpectedTotalPrice));

}
    @Test(priority = 3)
    public void CompletePage(){
    checkout=new CheckoutPages(driver);
    checkout.FinishButton().click();
    String Actualresult = driver.findElement(By.className("complete-header")).getText();
    String ExpectedResult = "Thank you for your order!";
    Assert.assertTrue(Actualresult.contains(ExpectedResult));
    checkout.BackHomeButton().click();
    Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "User wasn't redirected to home page.");
}
    @Test(priority = 4)
public void Logout(){
    checkout=new CheckoutPages(driver);
    products=new ProductsPage(driver);
    products.ClickOnSidebar().click();
    products.ClickOnLogoutLink().click();
    String actualResult = driver.getCurrentUrl();
    String expectedResult = "https://www.saucedemo.com/";
    Assert.assertEquals(actualResult, expectedResult, "URL did not match expected page URL.");
}
}
