package SeleniumPractices;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.Keywords;

public class WaitPractices {

	public static void main(String[] args) {
		
		WebDriver driver = Keywords.startTestOnChrome("https://tympanus.net/Development/PageLoadingEffects/index.html");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		String pageTitle = driver.findElement(By.tagName("h1")).getText();
		
		System.out.println(driver.findElement(By.id("loader")).isDisplayed());
		
		System.out.println("Test Step 1 - Verify homepage text 'Page Loading Effects':");
		if (pageTitle.contains("Page Loading Effects")) {
			System.out.println("Test Case Status: Pass");
		} else {
			System.out.println("Test Case Status: Failed");
		}
		
		driver.findElement(By.xpath("//a[text()='Show Page Loader']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));
			
		String secondPageTitle = driver.findElement(By.tagName("h2")).getText();
		System.out.println("Test Step 2 - Verify Second Page Title Text 'This is an example for a new page.':");
		if (secondPageTitle.contains("This is an example for a new page.")) {
			System.out.println("Test Case Status: Pass");
		} else {
			System.out.println("Test Case Status: Failed");
		}
		
		driver.findElement(By.xpath("//a[text()='Go back']")).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));

		pageTitle = driver.findElement(By.tagName("h1")).getText();
		
		System.out.println("Test Step 3 - Verify homepage text 'Page Loading Effects':");
		if (pageTitle.contains("Page Loading Effects")) {
			System.out.println("Test Case Status: Pass");
		} else {
			System.out.println("Test Case Status: Failed");
		}
		
		Keywords.endTest(driver);
	}

}
