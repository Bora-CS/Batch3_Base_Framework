package com.boratech.seleniumtest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeWorkWait1 {
public static void main(String[] args) {
	System.setProperty("webdriver.chrome.driver", "src/com/boratech/resources/chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.get("https://tympanus.net/Development/PageLoadingEffects/index.html");
	
	if (driver.getPageSource().contains("Page Loading Effects ")) {
		System.out.println("pass");
	}
	else {
		System.out.println("fail");
	}
	driver.findElement(By.xpath("//a[@href='#page-2'][@class='pageload-link']")).click();
	WebDriverWait wait = new WebDriverWait(driver, 20);
	WebElement goBack = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#page-1'][@class='pageload-link']")));
	
	if (driver.getPageSource().contains("This is an example for a new page.")) {
		System.out.println("new page pass");
	}
	else {
		System.out.println("new page fail");
	}
	goBack.click();
	
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	if (driver.getPageSource().contains("Page Loading Effects ")) {
		System.out.println("pass");
	}
	else {
		System.out.println("fail");
	}
	
	
	driver.close();
	driver.quit();
}
}
