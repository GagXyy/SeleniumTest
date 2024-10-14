package testCases;

import java.io.FileReader;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.time.Duration;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class ProgramPractice2 {
	public Logger logger;
	public Properties p;
	public WebDriver driver;
	FileReader file;
	@BeforeTest
	public void setup() throws IOException {
		
	//logger=LogManager.getLogger(this.getClass());
		file=new FileReader(".\\src\\test\\resources\\config.properties");

		p=new Properties();
		p.load(file);

	
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--incognito"); // Disable extensions
	
	//WebDriverManager.chromedriver().setup();
	//driver = new ChromeDriver();
	
		//System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver129.0.6668.90\\chromedriver-win64\\chromedriver.exe"); // https://googlechromelabs.github.io/chrome-for-testing/
	
	//driver.get(p.getProperty("appURL"));
	//driver.manage().window().maximize();
//	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	//driver.get(p.getProperty("appURL"));// Reading URL from Property.
		scheduleTestExecution();
	}
	private void scheduleTestExecution() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        // Set the time for scheduling (2:35 PM)
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 35);
        calendar.set(Calendar.SECOND, 0);
        
        // If it's already past 2:35 PM, schedule for the next day
        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        
        long initialDelay = calendar.getTimeInMillis() - System.currentTimeMillis();

        // Schedule the test to run
        scheduler.schedule(() -> {
            System.out.println("Running TestNG tests...");
            // Initialize the WebDriver and run the test
            runTest();
        }, initialDelay, TimeUnit.MILLISECONDS);
    }

    private void runTest() {
        // Setup WebDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito"); // Enable incognito mode
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver129.0.6668.90\\chromedriver-win64\\chromedriver.exe"); // https://googlechromelabs.github.io/chrome-for-testing/

      //  WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        
        // Maximize window and set timeouts
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Open the application URL
        driver.get(p.getProperty("appURL"));
        
        // Execute the searchProduct test
        try {
            try {
				searchProduct();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
            // Cleanup WebDriver
            driver.quit();
        }
    }

	    
	
	@Test
	public void searchProduct() throws InterruptedException
	{
		//logger.info("***Starting TC1_FlipcartEcommerceWebAppl Test***");	

		try {
			//Searching Product  
			
		WebElement searchbox=driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']"));
		searchbox.sendKeys(p.getProperty("searchProducts"));//Product Name
		searchbox.sendKeys(Keys.ENTER);	
		
		String wndHandle1=driver.getWindowHandle();
        System.out.println(wndHandle1);
		driver.findElement(By.xpath("//img[@class='DByuf4']")).click();//this will open a new tab
		//logger.info("***Clicked on IMAGE Nokia G42 5G ( 128 GB Storage, 6 GB RAM )Mobile***");	

		Thread.sleep(3000);
		Set<String> windowhandles=driver.getWindowHandles();
		Iterator<String> iterator =windowhandles.iterator();
		String parentwindow=iterator.next();
		String childtwindow=iterator.next();
		driver.switchTo().window(childtwindow);
		String currentTitle1=driver.getTitle();
		System.out.println(currentTitle1);

		driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]")).click();
		//logger.info("***Clicked on Go to cart***");	

		Thread.sleep(1000);
		String currentTitle2=driver.getTitle();
		
		
		WebElement addQuantity=driver.findElement(By.xpath("//div[@class='cPHDOP col-12-12']//div[3]//div[1]//div[1]//button[2]"));
		addQuantity.click();
		WebElement changeAddress=driver.findElement(By.xpath("//button[@class='KlGwJl']"));
		changeAddress.click();
		   Thread.sleep(2000);
		WebElement pincode=driver.findElement(By.xpath("//div//input[@placeholder='Enter pincode']"));
		pincode.sendKeys("561204");
		   Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[text()='Submit']")).click();

		
		WebElement address=driver.findElement(By.xpath("//span[@class='FE67T0']"));
		String addverify =address.getText();
		
		if(addverify.equals("Bangalore - 561"))
		{
		    System.out.println("Address is Matching");
	
		}else System.out.println("Address is NOT Matching");
		//else  Assert.fail();

       // Assert.assertEquals(addverify,p.getProperty("pincodeAddress"),"TestFailed");

		driver.findElement(By.xpath("//span[normalize-space()='Add Item']")).click();
	    Thread.sleep(2000);

		String addcartNotif=driver.findElement(By.xpath("//div[@class='eIDgeN']")).getText();
	    System.out.println(currentTitle2);
	   
         Assert.assertEquals(addcartNotif,"Item added to cart","TestFailed");
         //System.out.println(wndHandle2);
 	//	logger.info("*** TC1_FlipcartEcommerceWebAppl Test execution Completed***");	

	}
	 catch(Exception e)
      {
		//Assert.fail();

	   }
         
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.close();
	}
	}