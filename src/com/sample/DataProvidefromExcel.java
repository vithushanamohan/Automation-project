package com.sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataProvidefromExcel {
	String [][] data = null;
	
	@DataProvider(name="loginData")
	public String[][] loginDataProvider() throws BiffException, IOException {
		data = getExcelData();
		return  data;
	}
	
	public String[][] getExcelData() throws BiffException, IOException {
		FileInputStream excel = new FileInputStream("C:\\Users\\Admin\\Desktop\\excel\\Book1.xls");
		Workbook workbook = Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet(0);
		int rowscount = sheet.getRows();
		int columncount = sheet.getColumns();
		String testData[][] = new String[rowscount-1][columncount];
		
		for(int i=1;1<rowscount;i++) {
			for(int j=0;j<columncount;j++) {
				testData[i-1][j] = sheet.getCell(j,i).getContents();
			}
		}
		return testData;
		
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
		driver.quit();
	}
}
