package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";
    @Before
    public void setUp(){openBrowser(baseUrl);}

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() throws InterruptedException {
        //find the Username field and send the input
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
        // find the password field and send the input
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
        //find the login field and send the input
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        Thread.sleep(3000);

        String expectedTextSecureArea = "Secure Area";
        //find the message element and get the text
        String actualTextSecureArea = driver.findElement(By.xpath("//h2[text()=' Secure Area']")).getText();
        //comparing expected & actual messages
        Assert.assertEquals(expectedTextSecureArea,actualTextSecureArea);

    }
    @Test
    public void verifyTheUsernameErrorMessage(){
        //find the Username field and send the input
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith1");
        // find the password field and send the input
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
        //find the login field and send the input
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();

        String expectedInvalidUsernameMessage = "Your username is invalid!\n"+"×";
        //find the message element and get the text
        String actualInvalidUsernameErrorMessage = driver.findElement(By.xpath(" //div[@id='flash-messages']")).getText();
        //comparing expected & actual messages
        Assert.assertEquals(expectedInvalidUsernameMessage,actualInvalidUsernameErrorMessage);

    }
    @Test
    public void verifyThePasswordErrorMessage(){
        //find the Username field and send the input
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
        // find the password field and send the input
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword");
        //find the login field and send the input
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();

        String expectedInvalidPasswordErrorMessage = "Your password is invalid!\n"+"×";
        //find the message element and get the text
        String actualInvalidPasswordErrorMessage = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        //comparing expected & actual messages
        Assert.assertEquals(expectedInvalidPasswordErrorMessage,actualInvalidPasswordErrorMessage);
    }
    @After
    public void closeDown(){closeBrowser();}
}
