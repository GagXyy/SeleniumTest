package testCases;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class BaseClass{

	public static WebDriver driver;
	public static Properties p;
	public static Logger logger;
	@BeforeClass
	//@Parameters ({"os","browser"})
	public void setup() throws InterruptedException, IOException {
		
		
	 /*	switch(br.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
      default: System.out.println("Invalid Browser");return;
		
		}*/
		
		
	try(FileReader file =new FileReader("./src//test//resources//config.properties"))//Used try-with-resources. try catch block also allowed
	{
	p=new Properties();
	p.load(file);
	
	}
	logger=LogManager.getLogger(this.getClass());	
		

	
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	
	
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
   // driver.get(p.getProperty("appURL"));
    
	driver.manage().window().maximize();
	scheduleTest();
	}
	private void scheduleTest() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                // Launch the URL and run the test method
                try {
                   // driver.get(p.getProperty("appURL")); // Launch the URL
                   // Tc1_FlipcartApplications.addItemTo_cart(); // Call your test method
                } catch (Exception e) {
                    logger.error("Error during test execution: " + e.getMessage());
                } finally {
                    // Ensure the driver is closed after test execution
                    tearDown();
                }
            }
        }, getDelayUntilNextRun()); // Delay until the next run
    }

    private long getDelayUntilNextRun() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23); // Set hour to 7 PM
        calendar.set(Calendar.MINUTE, 25); // Set minute to 5
        calendar.set(Calendar.SECOND, 0); // Set seconds to 0

        long delay = calendar.getTimeInMillis() - System.currentTimeMillis();
        if (delay < 0) {
            // If the time has already passed today, schedule for tomorrow
            delay += 24 * 60 * 60 * 1000; // Add one day in milliseconds
        }
        return delay;
    }



	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
}