import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import java.io.IOException;

public class SenderAndReceiverInformationScreen {

    protected static String receiverName="Automation course";
    protected static String senderName="Daniel";
    protected static String blessing = "It's my second project. Well done to me!";
    //I did them static and protected in order to be able use them in "assertInformation" method from Extras class

    // setWhoToSendTo method - press "for someone else" button and enter this details: receiver name, sender name and blessing
    protected static void setWhoToSendTo (WebDriver driver) throws IOException {
        //1. "for someone else" button
        driver.findElement(By.xpath("//label[@data='forSomeone']")).click();
        //screen shot of Order Details
        Main.test.pass("Order Details Pic", MediaEntityBuilder.createScreenCaptureFromPath(Main.takeScreenShot("C:\\Users\\User\\Desktop\\QA experts\\Automation\\projects\\2\\report stuffs\\4_" + Main.currentTime)).build());
        //2. receiver name
        driver.findElement(By.xpath("//input[@data-parsley-required-message='מי הזוכה המאושר? יש להשלים את שם המקבל/ת']")).clear(); //my browser remember sometimes the name so i deleted it
        driver.findElement(By.xpath("//input[@data-parsley-required-message='מי הזוכה המאושר? יש להשלים את שם המקבל/ת']")).sendKeys(receiverName);
        //3. sender name
        driver.findElement(By.xpath("//input[@data-parsley-required-message='למי יגידו תודה? שכחת למלא את השם שלך']")).clear();
        driver.findElement(By.xpath("//input[@data-parsley-required-message='למי יגידו תודה? שכחת למלא את השם שלך']")).sendKeys(senderName);
        //4. blessing
        driver.findElement(By.tagName("textarea")).clear();
        driver.findElement(By.tagName("textarea")).sendKeys(blessing);
    }

    //uploadAPicAndSetWhenToSend - as it sounds: to upload a file and set when to send but and also pick the event
    protected static void uploadAPicAndSetWhenToSend(WebDriver driver) {
        //5. upload a picture
        driver.findElement(By.xpath("//input[@accept='image/*']")).sendKeys("C:\\Users\\User\\Desktop\\QA experts\\Automation\\projects\\2\\well done pic.jpg");
        //6. pick an event
        driver.findElement(By.linkText("לאיזה אירוע?")).click();
        driver.findElement(By.xpath("//li[@data-option-array-index='13']")).click();
        //7. "right after payment" button
        driver.findElement(By.className("send-now"));
    }

        //email option & email address
        protected static void setHowToSendIt(WebDriver driver){
        try{
                driver.findElement(By.className("btn-cancel-send-option")).click();  //my browser sometimes remember my mail - so it deletes the mail by press on X icon
        } catch (WebDriverException pressX_Exc){
                pressX_Exc.printStackTrace();
        }
        driver.findElement(By.className("icon-envelope")).click();  // 8. email option button
        driver.findElement(By.xpath("//input[@placeholder='כתובת המייל של מקבל/ת המתנה']")).clear();  // 9. email address
        driver.findElement(By.xpath("//input[@placeholder='כתובת המייל של מקבל/ת המתנה']")).sendKeys("QAExpertsss@gmail.com");  // 9. email address
        driver.findElement(By.className("btn-save")).click();// "save" (the email) button
        // 10.
        driver.findElement(By.xpath("//button[@data-toggle='modal']")).click(); //press payment
    }
}
