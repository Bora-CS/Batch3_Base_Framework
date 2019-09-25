package Quiz;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Quiz2 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://bora-test.s3-website-us-east-1.amazonaws.com/");	
		
		List<WebElement> signUpButtons = driver. findElements(By.xpath("//a[@href=\"register.html\"]"));
		System.out.println("Number of Sign Up buttons in HomePage: " + signUpButtons.size());
		System.out.println("=====================");

		
		driver.findElement(By.xpath("//a[@href=\"register.html\"]")).click();
		String signUpPage = driver.findElement(By.xpath("//p")).getText();
		if (signUpPage.equals("Create your Bora student account")) {
			System.out.println("Test Case == Sign Up Page");
			System.out.println("Test Case passed!");
			System.out.println("=====================");
		}
		driver.findElement(By.name("name")).sendKeys("Ahmed");
		driver.findElement(By.id("male")).click();
		driver.findElement(By.name("email")).sendKeys("blabla@gmail.com");
		
		WebElement course = driver.findElement(By.name("Course"));
		Select courseSelector = new Select(course);
		courseSelector.selectByValue("selenium");
		
		driver.findElement(By.name("password")).sendKeys("blablabla");
		driver.findElement(By.name("password2")).sendKeys("blablabla");
		driver.findElement(By.id("notarobot")).click();
		
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		String success = driver.findElement(By.xpath("//h4")).getText();
		if (success.equals("Congratulations!")) {
			System.out.println("Test Case == Success Page");
			System.out.println("Test Case passed!");
		}
		driver.findElement(By.xpath("//a[@href='https://www.bora-cs.com']")).click();
		
	}
	

}
