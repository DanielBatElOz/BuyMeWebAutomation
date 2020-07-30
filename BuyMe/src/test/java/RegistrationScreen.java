import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class RegistrationScreen {
    //Sign up step
    protected static void signUp(WebDriver driver) throws IOException {
        //click the first button: "sign in\login" button
        driver.findElement(By.className("seperator-link")).click();
        //click the second button: "sign in" button
        driver.findElement(By.className("text-btn")).click();
        //sign up- fill 4 text Fields: name, email, password, password validation
        driver.findElement(By.xpath("//input[@placeholder='שם פרטי']")).sendKeys("daniel"); //name
        driver.findElement(By.xpath("//input[@placeholder='מייל']")).sendKeys("jibrish@gmail.com"); //email
        driver.findElement(By.id("valPass")).sendKeys("Jibrish94"); //password
        driver.findElement(By.xpath("//input[@placeholder='אימות סיסמה']")).sendKeys("Jibrish94"); //password validation
        //screen shot of registration step
        Main.test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(Main.takeScreenShot("C:\\Users\\User\\Desktop\\QA experts\\Automation\\projects\\2\\report stuffs\\" + Main.currentTime)).build());
        //press "sign up to BUYME" button(last button)
        driver.findElement(By.className("orange")).click();

    }
    //Sign in step (login)
    protected static void logIn(WebDriver driver) throws IOException {
            //1. click the first button: "sign in\login" button
            driver.findElement(By.className("seperator-link")).click();
            //extra step - login with out entering credentials (asserting error messages)
            Extras.assertErrorMessages(driver);
            //2. sign in- fill 2 text Fields: email, password
            driver.findElement(By.xpath("//input[@placeholder='מייל']")).sendKeys("dindin@gmail.com");
            driver.findElement(By.xpath("//input[@placeholder='סיסמה']")).sendKeys("Danidin94");
            //screen shot of login step
            Main.test.pass("Login Screen Pic", MediaEntityBuilder.createScreenCaptureFromPath(Main.takeScreenShot("C:\\Users\\User\\Desktop\\QA experts\\Automation\\projects\\2\\report stuffs\\1_" + Main.currentTime)).build());
            //3. press "sign up to BuyMe" button(last button)
            driver.findElement(By.className("orange")).click();
    }
}
