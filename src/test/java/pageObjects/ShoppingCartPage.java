package pageObjects;

import testCases.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage {
	
public ShoppingCartPage(WebDriver driver) {
	super(driver);
}
@FindBy(xpath="//div[@class='cPHDOP col-12-12']//div[3]//div[1]//div[1]//button[2]")
WebElement addQuantity;
@FindBy(xpath="//button[@class='KlGwJl']")
WebElement changeAddress;
@FindBy(xpath="//span[@class='FE67T0']")
WebElement address;
@FindBy(xpath="//span[normalize-space()='Add Item']")
WebElement addItem;

@FindBy(xpath="//div[@class='eIDgeN']")
WebElement addItemNotfcn;


@FindBy(xpath="//div//input[@placeholder='Enter pincode']")
WebElement pincode ;
@FindBy(xpath="//div[text()='Submit']")
WebElement submit;

public void addQuantity() {
	addQuantity.click();
	
}
public String changeAddress() throws InterruptedException {
	
	changeAddress.click();
	pincode.sendKeys(BaseClass.p.getProperty("pinCode"));
	Thread.sleep(2000);//BaseClass b=new BaseClass(); using this object also can access p .
	submit.click();
	return address.getText();
}


public String addressCheck() {
	
	return address.getText();

}
public void AddItem() {
	
 addItem.click();
}

public String AddItemNoticifiation() {
	
	return addItemNotfcn.getText();
	}


}