package com.openmrs.Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.openmrs.pages.LoginPageTest;
import com.openmrs.pages.ServiceTypePage;

public class ServiceTypeTest {
	
	WebDriver driver;
	LoginPageTest loginpage;
	ServiceTypePage servicetytest;
	String serviceName = null;
	
	@BeforeClass
	@Parameters({"browserType","url"})
	public void invokeBrowser(String browserType,String url){		
			
			if(browserType.equals("IE"))
			{
			//IE
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			}
			else if(browserType.equals("CH"))
			{
			//Chrome
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			}
			else if(browserType.equals("FF"))
			{
			//Firefox
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
			}
			
			driver.get(url);
			servicetytest = new ServiceTypePage(driver);
			loginpage = new LoginPageTest(driver);
						
	}
	@AfterClass
	public void closeBrowser()
	{
		driver.close();
	}
	@BeforeMethod()
	public void login(){
		
		loginpage.login("Admin","Admin123");
	}
	@AfterMethod
	public void logout(){
		loginpage.logout();
	}
	@Test(priority=1)
    public void validateAddServiceType()
    {
 
		servicetytest.addServiceType("Nursing","30","Testing the new service");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        boolean result = servicetytest.verifyServiceType("Nursing");
        System.out.println(result);
        Assert.assertTrue(result);
    }
	 @Test(priority=2)
	public void validateEditServiceType()
	    {
		 	String newVerifytext = servicetytest.editServiceType("Nursing");
		 	serviceName = newVerifytext;
		 	System.out.println("Service name to edit " + serviceName);
		 	boolean result = servicetytest.verifyServiceType(newVerifytext);
	        Assert.assertTrue(result);
	    }
	 @Test(priority=3)
	public void validateDeleteServiceType()
	    {
		 	System.out.println("Service name to delete " + serviceName);
		 	servicetytest.deleteServiceType(serviceName);
		 	try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        boolean result = servicetytest.verifyServiceType(serviceName );
	        Assert.assertFalse(result);
	    }
	
}
