import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestLogin extends TestBase {


    LoginPage login;


    //Negative scenario
    @DataProvider(name = "InvalidLoginCredentials")
    public Object[][] invalidloginData() {
        return new Object[][]{

                {"media_ss", "secret_sauce"},
                {"standard_user", "Test_pass"},
                {"media_ss", "Test_pass"},
                {"", "secret_sauce"},
                {"standard_user", ""},
                {"locked_out_user", "secret_sauce"}


        };
    }

    @Test(dataProvider = "InvalidLoginCredentials")
    public void invalidLoginSteps(String username, String password) {
        driver.navigate().to("https://www.saucedemo.com/");
        login = new LoginPage(driver);

        login.loginSteps(username, password);

      if (username.equals("media_ss")){
          String actualResult = driver.getCurrentUrl();
          String expectedResult = "https://www.saucedemo.com/";
          Assert.assertEquals(actualResult, expectedResult, "URL did not match expected page URL.");
      } else if (password.equals("Test_pass")) {
          String actualResult = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
          String expectedResult = "Username and password do not match any user in this service";
          Assert.assertTrue(actualResult.contains(expectedResult)," Error message did not match expected text. ");
      }else if(username.isEmpty()){
          String actualResult = driver.getCurrentUrl();
          String expectedResult = "https://www.saucedemo.com/";
          Assert.assertEquals(actualResult, expectedResult, "URL did not match expected page URL.");}
          else if (username.equals("locked_out_user")) {
              String actualResult = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
              String expectedResult = "Sorry, this user has been locked out.";
              Assert.assertTrue(actualResult.contains(expectedResult), " Error message did not match expected text.");

    }}

    //Positive scenario
    @DataProvider(name = "ValidLoginCredentials")
    public Object[][] validLoginData() {
        return new Object[][]{
                {"visual_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"standard_user", "secret_sauce"}

        };
    }


    @Test(dataProvider = "ValidLoginCredentials")
    public void validLoginSteps(String username, String password) {
        driver.navigate().to("https://www.saucedemo.com/");
        login = new LoginPage(driver);
        login.loginSteps(username, password);
        if (username.equals("standard_user")) {
            String actualResult = driver.getCurrentUrl();
            String expectedResult = "https://www.saucedemo.com/inventory.html";
            Assert.assertEquals(actualResult, expectedResult, "URL did not match expected page URL.");
        }
    }}