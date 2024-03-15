import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.List;

public class Tests {
    private WebDriver driver;

    String username="standard_user";
    String password="secret_sauce";
    String itemName="Sauce Labs Backpack";

    By usernameField=By.xpath("//input[@ placeholder='Username']");
    By passwordField=By.id("password");
    By loginField=By.id("login-button");
    By cartItem=By.className("shopping_cart_link");
    By item=By.className("inventory_item_name");





/* with out using the below function        //bs kda msh hyb2a generic 3shan h7tag aktb el 3 stoor kol ma age a3ml locator bnfs el tre2a f a7sn ene a3ml fun hya tb2a generic
    String locator=String.format("//div[@class='inventory_item_name '][contains(text(),'%s')]/parent::a/../following-sibling::div/button",itemName);
    By anyItem=By.xpath(locator);
 */


    @Test
    public void testCase1(){


        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);


        driver.get("https://www.saucedemo.com/");
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginField).click();

      //Task1
        List<WebElement> webElement = driver.findElements(item);

        Assert.assertTrue(webElement.getFirst().isDisplayed());
        Assert.assertTrue(webElement.getLast().isDisplayed());
        Assert.assertEquals(webElement.size(),6);
     //..............


      //Task2


    /* without using the below function
      WebElement webElement  =driver.findElement(anyItem);
        webElement.click();

        driver.findElement(cartItem).click();
        WebElement element2=driver.findElement(item);
        Assert.assertEquals(element2.getText(),itemName);

     */

        WebElement element= addItem(itemName);
        element.click();

        driver.findElement(cartItem).click();
        WebElement element2=driver.findElement(item);
        Assert.assertEquals(element2.getText(),itemName);


    }


    public WebElement addItem(String item){

        String locator=String.format("//div[@class='inventory_item_name '][contains(text(),'%s')]/../../following-sibling::div/button",item);
        WebElement webElement =driver.findElement(By.xpath(locator));
        return webElement;
    }


}
