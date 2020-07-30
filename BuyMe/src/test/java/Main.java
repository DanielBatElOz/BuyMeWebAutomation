import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Main {
    private static WebDriver driver;

    // create ExtentReports and attach reporter
    private static ExtentReports extent ;
    // creates a toggle for the given test, adds all log events under it
    protected static ExtentTest test ;
    protected static String currentTime;

    //unique daytime for video so we can reduce duplicate
    static DateFormat df = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
    static Date dateObj = new Date();
    //video folder path and the name of the video file
    static String videoFolder = "C:\\Users\\User\\Desktop\\QA experts\\Automation\\projects\\2\\report stuffs\\";
    static String videoFile = "TestVideo-"+df.format(dateObj);
    //record object
    static ATUTestRecorder recorder;


    @BeforeClass
    public static void setUp() throws IOException, SAXException, ParserConfigurationException, ATUTestRecorderException {

        //reports statements:
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\User\\Desktop\\QA experts\\Automation\\projects\\2\\report stuffs\\buymeReport.html");
        // attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // name your test and add description
        test = extent.createTest("myBuyMeTest", "testing BuyME website purchases");
       //time stuff of screen shot
        Main.currentTime=  String.valueOf(System.currentTimeMillis());

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");

        //initializing the browser from XML file
        String BrowserType = getData ("BrowserType");
        if (BrowserType.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\QA experts\\Automation\\selenium\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver(options);
        }else if (BrowserType.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\User\\Desktop\\QA experts\\Automation\\installations\\geckodriver.exe");
            driver= new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(getData("URL"));
        driver.manage().window().maximize();
        recorder = new ATUTestRecorder(videoFolder, videoFile, false);
        recorder.start();

    }

    @AfterClass
    public static void runOnceAfterClass() throws ATUTestRecorderException {
        driver.quit();
        recorder.stop();
        test.log(Status.INFO, "<a href='"+videoFolder + videoFile + ".mov"+"'><span class='label info'>Download video</span></a>");
        extent.flush();
    }

    //Registration Screen Test
    @Test
    public void test01 () throws IOException {
        //RegistrationScreen.signUp(driver);
        //or
        RegistrationScreen.logIn(driver);

    }
    //Home Screen Test
    @Test
    public void test02 () throws IOException {
        HomeScreen.chooseTheGiftProperties(driver);
    }
    //Choose Gift Screen Test
    @Test
    public void test03() throws ParserConfigurationException, SAXException, IOException, InterruptedException {
        ChooseGiftScreen.assertURLs(driver);
        Extras.takeABottomScreenshot(driver);
        ChooseGiftScreen.pickTheBusiness(driver);
    }
    //Sender and Receiver Information Screen Test
    @Test
    public void test04() throws IOException {
        SenderAndReceiverInformationScreen.setWhoToSendTo(driver);
        SenderAndReceiverInformationScreen.uploadAPicAndSetWhenToSend(driver);
        SenderAndReceiverInformationScreen.setHowToSendIt(driver);
    }


    //Extras steps (most of them are here, and one is above in test03)
    @Test
    public void test05(){
        //assert: sender, receiver and blessing
        Extras.assertInformation(driver);
    }
    @Test
    public void test06() {
        //name step color asserting
        Extras.assertNameStepColor(driver);
    }
    @Test
    public void test07() throws SAXException, ParserConfigurationException, AWTException, IOException {
        //locating a dot and printing it's dimensions
        Extras.enterLoadingScreen(driver);
    }


    //A method to read XML files
    protected static String getData(String keyName) throws ParserConfigurationException, IOException, SAXException {
        File configXmlFile = new File("C:\\Users\\User\\Desktop\\QA experts\\Automation\\projects\\2\\URL and Browser.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(configXmlFile);
        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }
    //A method to take screen shot
    protected static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }

}
