package SeleniumPractices;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.Keywords;
import net.bytebuddy.asm.Advice.Enter;

public class HomeWork1 {

	public static void main(String[] args) {
		WebDriver driver = Keywords.startTestOnChrome("https://tympanus.net/Development/PageLoadingEffects/index.html");
		WebElement homePage = driver.findElement(By.cssSelector(".pageload-link"));
		if (homePage.getText().equals("SHOW PAGE LOADER")) {
			System.out.println("We made it to homepage = " + homePage.getText());
		}else {
			System.out.println("Test Error = Homepage could not find!");
		}
		
		homePage.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='page-2'][@class='container show']")));
		String secondPageText = driver.findElement(By.xpath("//h2[text()='This is an example for a new page.']")).getText();
		if (secondPageText.equals("This is an example for a new page.")) {
			System.out.println("We made it to second page = " + secondPageText);
		}else {
			System.out.println("Test Error = Second Page could not find!");
		}
		
		
	
		WebElement element = driver.findElement(By.xpath("//a[@href='#page-1'][@class='pageload-link']"));
		element.sendKeys(Keys.ENTER);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='page-1'][@class='container show']")));
		if (homePage.getText().equals("SHOW PAGE LOADER")) {
			System.out.println("We made it to homepage = " + homePage.getText());
		}else {
			System.out.println("Test Error = Homepage could not find!");
		}
		Keywords.endTest(driver);
	}

}
