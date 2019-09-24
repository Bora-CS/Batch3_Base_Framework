package SeleniumPractices;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.Keywords;

public class CostcoTest {

	public static void main(String[] args) {
		WebDriver driver = Keywords.startTestOnChrome("https://www.costco.com/");
		
		FluentWait<WebDriver> advancedWait = new FluentWait<WebDriver>(driver)
													.withTimeout(60, TimeUnit.SECONDS)
													.pollingEvery(1, TimeUnit.SECONDS)
													.ignoring(NoSuchElementException.class);
		
		WebElement theSlide = advancedWait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				WebElement targetElement = driver.findElement(By.xpath("//a[contains(@id,'slick-slide')]/img[contains(@alt,'Apple Watch Series 5 GPS')]"));
				if (targetElement.isDisplayed()) {
					return targetElement;
				} else {
					return null;
				}
			}
		});
		
		theSlide.click();
		
		Keywords.endTest(driver);
	}

}
