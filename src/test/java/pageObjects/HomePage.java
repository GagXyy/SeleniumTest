package pageObjects;

	
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;

	public class HomePage extends BasePage {
		//WebDriver driver;
	public HomePage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//input[@placeholder='Search for Products, Brands and More']")
	WebElement searchField;
	@FindBy(xpath="//div[@class='yPq5Io']//div//img[@alt='Nokia G42 5G (So Grey, 128 GB)']")
	WebElement itemImage;
	
 
	public void searchItem(String item) {
		searchField.sendKeys(item);
		searchField.sendKeys(Keys.ENTER);
		String wndHandle1=driver.getWindowHandle();
        System.out.println(wndHandle1);
	}
	public void clickItem() throws InterruptedException {
		itemImage.click();
		Thread.sleep(3000);
		Set<String> windowhandles=driver.getWindowHandles();
		Iterator<String> iterator =windowhandles.iterator();
		String parentwindow=iterator.next();
		String childtwindow=iterator.next();
		driver.switchTo().window(parentwindow);
		String currentTitle1=driver.getTitle();
		System.out.println(currentTitle1);
		
	        }
	    }
		
	
