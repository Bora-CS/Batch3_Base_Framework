package Quiz;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Quiz3 {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://bora-test.s3-website-us-east-1.amazonaws.com/");
		
		
		List<WebElement> logInButtons = driver. findElements(By.xpath("//a[@href=\"login.html\"]"));
		System.out.println("Number of Sign Up buttons in HomePage: " + logInButtons.size());
		System.out.println("=====================");	
		driver. findElement(By.xpath("//a[@href=\"login.html\"]")).click();
		
		String logInPage = driver.findElement(By.tagName("p")).getText();
		if (logInPage.equals("Sign in to your Bora student account")) {
			System.out.println("Test Case 1 === Sign in Page");
			System.out.println("Test Case 1 passed!");
			System.out.println("=====================");	

		}
		driver.findElement(By.name("email")).sendKeys("blabla@gmail.com");
		driver.findElement(By.name("password")).sendKeys("blablabla");
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		String profilePage = driver.findElement(By.tagName("h1")).getText();
		String skillProfile = driver.findElement(By.tagName("h4")).getText();

		if (profilePage.equals("Student Profile") && skillProfile.equals("Skill Set")) {
			System.out.println("Test Case 2 === Profile Page, Skill Set");
			System.out.println("Test Case 2 passed!");
			System.out.println("=====================");	

		}
		try {
			driver.findElement(By.xpath("//a[@class='nav-link'][@data-toggle='modal']"));
			System.out.println("Log Out button found!");
		} catch (NoSuchElementException e) {
			System.out.println("Error! No Element Found!");
		}
		driver.findElement(By.xpath("//a[@class='nav-link'][@data-toggle='modal']")).click();
		
		
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Sure')]")));
		
		
		String modal = driver.findElement(By.id("exampleModalCenterTitle")).getText();
		System.out.println(modal);
		if (modal.contains("Are you sure")) {
			System.out.println("Test Case 3 === Are you Sure Modal");
			System.out.println("Test Case 3 passed!");
			System.out.println("=====================");	
		}
		
		driver.findElement(By.xpath("//button[contains(text(), 'Sure')]")).click();
		profilePage = driver.findElement(By.tagName("h1")).getText();
		skillProfile = driver.findElement(By.tagName("h4")).getText();

		if (profilePage.equals("Student Profile") && skillProfile.equals("Skill Set")) {
			System.out.println("Test Case 4 === Profile Page, Skill Set");
			System.out.println("Test Case 4 passed!");
			System.out.println("=====================");	

		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//body[@class='modal-open']")));
		driver.findElement(By.xpath("//a[@class='nav-link'][@data-toggle='modal']")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Gotta go!']")));
		driver.findElement(By.xpath("//a[text()='Gotta go!']")).click();
		
		String homePage = driver.findElement(By.tagName("h1")).getText();
		if (homePage.equals("Bora UI Automation Test")) {
			System.out.println("Test Case 5 === HomePage");
			System.out.println("Test Case 5 passed!");
			System.out.println("=====================");
		}
		

	}

}
