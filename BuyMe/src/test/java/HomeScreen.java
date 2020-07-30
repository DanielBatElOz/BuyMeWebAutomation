import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.IOException;


public class HomeScreen {
        //choose a gift page
    protected static void chooseTheGiftProperties(WebDriver driver) throws IOException {
        //1.pick a price point
        driver.findElement(By.linkText("סכום")).click(); //press "סכום" button
        driver.findElement(By.xpath("//li[@data-option-array-index='6']")).click(); //choosing the price
        //2.pick the area
        driver.findElement(By.linkText("אזור")).click();
        driver.findElement(By.xpath("//li[@data-option-array-index='1']")).click();
        //3.pick category
        driver.findElement(By.linkText("קטגוריה")).click();
        driver.findElement(By.xpath("//li[@data-option-array-index='1']")).click();
        //screen shot of Choosing A gift step
        Main.test.pass("Home Screen Pic", MediaEntityBuilder.createScreenCaptureFromPath(Main.takeScreenShot("C:\\Users\\User\\Desktop\\QA experts\\Automation\\projects\\2\\report stuffs\\2_" + Main.currentTime)).build());
        //4.press the search button
        driver.findElement(By.linkText("תמצאו לי מתנה")).click();
    }
}
