package SeleniumPractices;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.Keywords;

public class HomeWork2 {

	public static void main(String[] args) {
		
		WebDriver driver = Keywords.startTestOnChrome("https://www.costco.com/");
		WebDriverWait wait = new WebDriverWait(driver, 45);
		wait.until(ExpectedConditions.presenceOfElementLocated
				(By.xpath("//a[@href='/CatalogSearch?dept=All&keyword=appleseptmvm19'][@role='tabpanel'][@aria-hidden='false']")));
		driver.findElement(By.id("hero-carousel")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,\"100497427\")]")));
		String appleText = driver.findElement(By.id("rsltCntMsg")).getText();
		if (appleText.contains("apple")) {
			System.out.println("Test passed! = " + appleText);
		}
		
		driver.findElement(By.xpath("//a[contains(@href,\"100497427\")]")).click();
		String itemVerify = driver.findElement(By.xpath("//span[@itemprop='sku']")).getAttribute("data-sku");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-btn")));
		driver.findElement(By.id("add-to-cart-btn")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-primary.btn-block")));
		driver.findElement(By.cssSelector(".btn.btn-primary.btn-block")).click();
		
		String cartItemNumber = driver.findElement(By.xpath("//div[@class='order-item']")).getAttribute("data-orderitemnumber");
		if (cartItemNumber.equals(itemVerify)) {
			System.out.println("Test passed = "+ "Cart Item No ="+cartItemNumber + " Item Verify = " + itemVerify);
		}
		else {
			System.out.println("Test failed!" + "Cart Item No ="+cartItemNumber + " Item Verify = " + itemVerify);
		}
		
		try {
			driver.findElement(By.id("shopCartCheckoutSubmitButton"));
			System.out.println("Checkout button found!");
		} catch (NoSuchElementException e) {
			System.out.println("Checktout button could not be found!");
		}
		
		Keywords.endTest(driver);
	}
	
	
}
