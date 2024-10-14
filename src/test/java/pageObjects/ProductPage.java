package pageObjects;

import java.time.Duration;
//import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {
	
public ProductPage(WebDriver driver) {
	super(driver);
}
@FindBy(xpath="//button[@class='QqFHMw vslbG+ In9uk2']")
//button[@class='QqFHMw vslbG+ In9uk2']
WebElement GotoCart;


public void switchToWindow() {
	/*Set<String> windowhandles=driver.getWindowHandles();
	Iterator<String> iterator =windowhandles.iterator();
	String parentwindow=iterator.next();
	String childtwindow=iterator.next();
	driver.switchTo().window(childtwindow);*/
	
	        // Store the current window handle
	        String mainWindow = driver.getWindowHandle();

	        // Wait for a new window to open (can adjust the wait time)
	        new WebDriverWait(driver, Duration.ofSeconds(10))
	            .until(ExpectedConditions.numberOfWindowsToBe(2));

	        // Get all window handles
	        Set<String> allWindows = driver.getWindowHandles();

	        // Switch to the new window
	        for (String window : allWindows) {
	            if (!window.equals(mainWindow)) {
	                driver.switchTo().window(window);
	                break; // Switch to the new window and exit the loop
	            }
	        }
	    }
public void clickGotoCart() {
	GotoCart.click();
System.out.println("clicked on Go to cart");

}

}