import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


import static org.junit.Assert.assertEquals;

public class ChooseGiftScreen {

    //Assert URLs: URL of home page VS. new URL after pressing search button
    protected static void assertURLs(WebDriver driver) throws IOException, SAXException, ParserConfigurationException, InterruptedException {
        String actualURL = Main.getData("URL");
        Thread.sleep(3000); //i suspend execution for a specified period in order to get the new different URL (of the next page)
        try {
            assertEquals(actualURL,driver.getCurrentUrl());
            System.out.println("It's the same URL");
        }catch (AssertionError exception){
            exception.printStackTrace();
        }
    }

    //picking the business and the specific gift
    protected static void pickTheBusiness (WebDriver driver) throws IOException {
        //screen shot of Choose Gift step
        driver.findElement(By.partialLinkText("בית הספר האינטרנטי לצילום")).click();// pick the business
        Main.test.pass("Choose Gift Pic", MediaEntityBuilder.createScreenCaptureFromPath(Main.takeScreenShot("C:\\Users\\User\\Desktop\\QA experts\\Automation\\projects\\2\\report stuffs\\3_" + Main.currentTime)).build());
        driver.findElement(By.partialLinkText("קורס פוטושופ")).click(); // pick the specific gift from the business

    }
}

