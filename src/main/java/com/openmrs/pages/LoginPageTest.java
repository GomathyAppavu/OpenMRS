package com.openmrs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPageTest {
	
	WebDriver driver;
	
	public LoginPageTest(WebDriver driver1){
		driver = driver1;
	}
	
	
	public boolean login(String username,String pwd)
	{	
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.id("Inpatient Ward")).click();
		driver.findElement(By.id("loginButton")).click();
		//Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Logged in as Super')]")).isDisplayed());
		if(driver.findElement(By.xpath("//h4[contains(text(),'Logged in as Super')]")).isDisplayed())
			return true;
		else
			return false;
			
	}
	
	public void logout()
	{
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
	}

}
