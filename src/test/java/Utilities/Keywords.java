package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Keywords {
	public static void waitFor (int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkIfElementExists(WebDriver driver, By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static WebDriver startTestOnChrome(String applicationURL) {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get(applicationURL);
		return driver;
	}
	
	public static void reportTestStep (boolean condition, String testStep) {
		System.out.println("Test Step:\t" + testStep);
		if (condition) {
			System.out.println("Status:\t" + "Passed");
		} else {
			System.out.println("Status:\t" + "Failed");
		}
	}

	public static void endTest(WebDriver driver) {
		driver.close();
		driver.quit();
	}
	
	public static void highlight(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;')", element);
	}
}
