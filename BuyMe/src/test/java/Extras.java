import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Extras {

//Loading Screen -
    // printing the first spinner's/first loading dot's size
    protected static void enterLoadingScreen(WebDriver driver) throws IOException, SAXException, ParserConfigurationException, AWTException {
        driver.navigate().to(Main.getData("URL")); //enter this website again in order to locate the spinner (just want to see the loading page part)
        Robot rF12 = new Robot();
        Robot rF8 = new Robot();
        rF12.keyPress(KeyEvent.VK_F12); // to activate Developer Options
        rF8.keyPress(KeyEvent.VK_F8);  // freezes the DOM to capture the elements I need.
        driver.get("http://buyme.co.il");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"app-loading-img\"]/div/div[1]")); //locating a dot
        System.out.println("Width: " +element.getSize().getWidth()); //printing his width
        System.out.println("Height: " +element.getSize().getHeight()); //printing his height
        rF8.keyRelease(KeyEvent.VK_F8);
    }

//Home Screen
    protected  static void assertErrorMessages (WebDriver driver) {
        //press "sign up to BuyMe" button(last button) [no credentials entered]
        driver.findElement(By.className("orange")).click();
        //save error messages in array and then split them into string variables
        List <WebElement> list = driver.findElements(By.className("parsley-errors-list"));
        String text1 = list.get(0).getText();
        String text2 = list.get(1).getText();
        //3. assert Error Messages
        try {
            assertEquals(text1, text2);
            System.out.println("Error messages are the same!");
        }catch (AssertionError errorMessagesExc){
            errorMessagesExc.printStackTrace();
            System.out.println("Error messages are the different!");
        }
    }

//Choose Gift Screen
    protected static void takeABottomScreenshot(WebDriver driver) throws IOException {
        //scrolling to the bottom of the screen
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        //taking a screenshot for the report
        Main.test.pass("Bottom of the page Pic(extra step)", MediaEntityBuilder.createScreenCaptureFromPath(Main.takeScreenShot("C:\\Users\\User\\Desktop\\QA experts\\Automation\\projects\\2\\report stuffs\\bottomOfThePage_" + Main.currentTime)).build());
    }


//Sender & Receiver information screen
    //Asserting the text color of the step name
    protected static void assertNameStepColor(WebDriver driver) {
        try {
            String rgbaStepColor = driver.findElement(By.xpath("//div[@class='step-title highlighted']")).getCssValue("color");
            String hexStepColor = Extras.getRGBColorReturnHexColor(rgbaStepColor);
            String yellowHex="#fab442";
            assertEquals(yellowHex, hexStepColor);
            System.out.println("Same color");
        }catch(AssertionError colorExc){
            colorExc.printStackTrace();
            System.out.println("different color");
        }
    }
    //Comparing the information I filled up (Sender name, Receiver name, Blessing) with the information displayed on the left side of gift preview screen
    protected static void assertInformation(WebDriver driver) {
        //assert sender name, receiver name and blessing
        //assert receiver name
        assertEquals(SenderAndReceiverInformationScreen.receiverName, driver.findElement(By.xpath("//div/div[2]/div[2]/span[2]")).getText());
        //assert sender name
        assertEquals(SenderAndReceiverInformationScreen.senderName, driver.findElement(By.xpath("//div/div[2]/div[3]/span[2]")).getText());
        //assert blessing
        assertEquals(SenderAndReceiverInformationScreen.blessing, driver.findElement(By.className("cut-greeting")).getText());
        System.out.println("Sender name, receiver name and blessing are the same!");
    }


    private static String getRGBColorReturnHexColor(String colorOfSendTo) {
        String[] hexValue = colorOfSendTo.replace("rgba(", "").replace(")", "").split(",");

        int hexValue1 = Integer.parseInt(hexValue[0]);
        hexValue[1] = hexValue[1].trim();
        int hexValue2 = Integer.parseInt(hexValue[1]);
        hexValue[2] = hexValue[2].trim();
        int hexValue3 = Integer.parseInt(hexValue[2]);


        return String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
    }

}
