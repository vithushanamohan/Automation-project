package com.sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class loginExample {
	
	String [][] data = {
			{"Admin","admin123"},
			{"Admin1","admin123"},
			{"Admin","admin1"},
			{"Admin1","admin1"}
	};
	
	@DataProvider(name="loginData")
	public String[][] loginDataProvider() {
		return  data;
	}
	
	@Test(dataProvider = "loginData")
	public void loginwithusernamePassword(String uname,String pword) throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "C:\\My Account\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(3000);
		WebElement username = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input"));
		username.sendKeys(uname);
		WebElement password = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input"));
		password.sendKeys(pword);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
		//driver.quit();
	}
}
