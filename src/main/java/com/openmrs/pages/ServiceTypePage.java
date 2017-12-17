package com.openmrs.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;




public class ServiceTypePage {

	WebDriver driver;
	
	public ServiceTypePage(WebDriver driver1)
	{
		driver = driver1;
	}
	
	public void addServiceType(String serviceName,String duration,String description)
	{
		driver.findElement(By.xpath("//a[@id='appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension']/i")).click();
		driver.findElement(By.xpath("//a[@id='appointmentschedulingui-manageAppointmentTypes-app']/i")).click();
		driver.findElement(By.xpath("//button[@class='confirm appointment-type-label right']/i")).click();
		driver.findElement(By.id("name-field")).clear();
		driver.findElement(By.id("name-field")).sendKeys(serviceName);
		driver.findElement(By.id("duration-field")).sendKeys(duration);
		driver.findElement(By.id("description-field")).sendKeys(description);
		driver.findElement(By.id("save-button")).click();
		
	}
	public String editServiceType(String serviceName)
	{
		Random iRanNumber =  new Random();
		String newServiceName = serviceName+iRanNumber.nextInt();
		driver.findElement(
				By.xpath("//a[@id='appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension']/i"))
				.click();
		driver.findElement(
				By.xpath("//a[@id='appointmentschedulingui-manageAppointmentTypes-app']/i"))
				.click();

		List<WebElement> size = driver.findElements(By
				.xpath("//span/a[@tabindex='0']"));
		for (int j = 0; j < size.size(); j++) {
			List<WebElement> allOptions = driver.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
			boolean isAvailabe = false;
			for (int i = 0; i < allOptions.size(); i++) {
				//System.out.println(allOptions.get(i).getText());
				if ((allOptions.get(i).getText()).equals(serviceName)) {
					String sService = serviceName;
					driver.findElement(
							By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[contains(text(),\'"+sService+"\')]/following-sibling::td[3]/span/i[@title='Edit']")).click();
							
					driver.findElement(By.id("name-field")).clear();
					driver.findElement(By.id("name-field")).sendKeys(newServiceName);
					driver.findElement(By.id("duration-field")).clear();
					driver.findElement(By.id("duration-field")).sendKeys("30");
					driver.findElement(By.id("description-field")).clear();
					driver.findElement(By.id("description-field")).sendKeys(
							"Nursing will do all the necessary help");
					driver.findElement(By.id("save-button")).click();
					isAvailabe = true;
					break;
				}
			}
			if(!isAvailabe)
				driver.findElement(By.id("appointmentTypesTable_next")).click();
			else
				break;

		}
		return newServiceName;
	}
	public void deleteServiceType(String serviceName)
	{
		driver.findElement(By.xpath("//a[@id='appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension']/i")).click();
		driver.findElement(By.xpath("//a[@id='appointmentschedulingui-manageAppointmentTypes-app']/i")).click();
		//driver.findElement(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[contains(text(),'nullNursing-staff')]/following-sibling::td[3]/span/i[@title='Delete']")).click();
		List<WebElement> size = driver.findElements(By
				.xpath("//span/a[@tabindex='0']"));
		for (int j = 0; j < size.size(); j++) {
			List<WebElement> allOptions = driver
					.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
			boolean isAvailabe = false;
			for (int i = 0; i < allOptions.size(); i++) {
				//System.out.println(allOptions.get(i).getText());
				if ((allOptions.get(i).getText()).equals(serviceName)) {
					driver.findElement(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[contains(text(),\'"+serviceName+"\')]/following-sibling::td[3]/span/i[@title='Delete']")).click();
					//driver.findElement(By.xpath("button[@class='confirm right']")).click();
					WebElement e =driver.switchTo().activeElement();
					e.findElement(By.xpath("//div[@class='simplemodal-wrap']/div/div[2]/button[@class='confirm right']")).click(); 
					isAvailabe = true;
					break;
				}
			}
			if(!isAvailabe)
				driver.findElement(By.id("appointmentTypesTable_next")).click();
				else
					break;
			}

	}
	public boolean verifyServiceType(String serviceName)
	{
		List<WebElement> pageSize = driver.findElements(By
				.xpath("//span/a[@tabindex='0']"));
		boolean isAvailabe = false;
		System.out.println("Page size " + pageSize);
		for (int j = 0; j < pageSize.size(); j++) {
			List<WebElement> allOptions = driver
					.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
			//boolean isAvailabe = false;
			for (int i = 0; i < allOptions.size(); i++) {
				System.out.println(allOptions.get(i).getText());
				if ((allOptions.get(i).getText()).equalsIgnoreCase(serviceName)) {
					System.out.println("Service is available");
					isAvailabe =true;
					break;
				}
			}
				if(!isAvailabe)
					driver.findElement(By.id("appointmentTypesTable_next")).click();
				else
				{
					System.out.println("Service is available");
					break;
				}
					
				
		}
		return isAvailabe;
		
	}
}
