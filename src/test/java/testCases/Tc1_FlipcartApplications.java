package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.ShoppingCartPage;


public class Tc1_FlipcartApplications extends BaseClass {
	
 
	@Test
	public static void addItemTo_cart() throws InterruptedException {
		logger.info("***Starting TC1_AccountRegistration Test***");	

      try {
    	  driver.get(p.getProperty("appURL"));
	     HomePage hp=new HomePage(driver);		
		hp.searchItem(p.getProperty("searchProducts"));
		
		Thread.sleep(2000);
		System.out.println("Item Searched ");
		Thread.sleep(2000);
		hp.clickItem();
		
		logger.info("***Item Clicked and moved to new tab***");    	
		
		logger.info("Clicked on Item");	 

	ProductPage pPage=new ProductPage(driver);
	logger.info("Switching to ProductWindow");
	pPage.switchToWindow();
	logger.info("swirtched new window");
	
	Thread.sleep(2000);
	
	pPage.clickGotoCart();
	logger.info("Clicked Goto cart");

	Thread.sleep(2000);

	ShoppingCartPage scartPage=new ShoppingCartPage(driver);
	scartPage.addQuantity();
	logger.info("Item Quantity added");
	
	Thread.sleep(2000);
	
	String pincodeAddress=scartPage.changeAddress();
	Thread.sleep(2000);
	
if (pincodeAddress.equals(p.getProperty("pincodeAddress")))
{
	System.out.println("Address matching ");
}
Thread.sleep(2000);

scartPage.AddItem();
logger.info("Clicked on Add item Button ");

Thread.sleep(2000);
String notification =scartPage.AddItemNoticifiation();    

Thread.sleep(2000);
	Assert.assertEquals( notification,"Item added to cart","Test failed ");
	logger.info("***  TC1_AccountRegistration test Executed successfully***");	
	}
		catch(Exception e) {
			
			Assert.fail();
		}
	} 
	
}