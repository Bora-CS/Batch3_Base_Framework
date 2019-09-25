package SeleniumPractices;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.boratech.util.Keywords;

public class ExplicitWait {
	public static void main(String[] args) {
		WebDriver driver = Keywords.startTestOnChrome("https://tympanus.net/Development/PageLoadingEffects/index.html");

		if (driver.getPageSource().contains("Page Loading Effects ")) {
			System.out.println("Fisrt Page");
		} else {
			System.out.println("Failed");
		}
		driver.findElement(By.xpath("//a[@href='#page-2'][@class='pageload-link']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement goBack = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//a[@href='#page-1'][@class='pageload-link']")));

		if (driver.getPageSource().contains("This is an example for a new page.")) {
			System.out.println("Second Page");
		} else {
			System.out.println("Second Page Failed");
		}
		goBack.sendKeys(Keys.ENTER);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (driver.getPageSource().contains("Page Loading Effects ")) {
			System.out.println("Back to The First Page");
		} else {
			System.err.println("Failed");
		}

		driver.close();
		driver.quit();
	}
}