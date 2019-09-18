package seleniumPractices;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.Keywords;

public class AmazonTest {

	public static WebDriver driver;
	public static void main(String[] args) {
		
		try {
			driver = Keywords.startTestOnChrome("https://www.amazon.com");
			action();		
	} catch (Exception e) {
		e.printStackTrace();
		
	} finally {
		Keywords.endTest(driver);
	}
		
	}
	private static void action() {
	WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
	searchBox.sendKeys("Shampoo");
	String parentXpath = "//div[contains(@data-cel-widget, 'search_result_')]";
    String titleXpath = "//span[@class='a-size-base-plus a-color-base a-text-normal']";
    String priceXpath = "//span[@class='a-price']/span[@class='a-offscreen']";
	List<WebElement> results = driver.findElements(By.xpath(parentXpath));
	
	for (int i = 1; i <results.size(); i++) {
	
		// get the title
	String title =	driver.findElement(By.xpath(parentXpath + i + titleXpath)).getText();
	
	String price =	driver.findElement(By.xpath(parentXpath + i + priceXpath)).getText();

	
	
	}
		
	}
		
}