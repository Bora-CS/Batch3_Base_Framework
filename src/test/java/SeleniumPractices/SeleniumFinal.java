package SeleniumPractices;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.Keywords;

public class SeleniumFinal {

	public static void main(String[] args) {

		WebDriver driver = Keywords
				.startTestOnChrome("http://bora-test.s3-website-us-east-1.amazonaws.com/landing.html");
		driver.findElement(By.partialLinkText("Students")).click();

		String studentPageTitle = driver.findElement(By.tagName("h1")).getText();

		Keywords.waitFor(2);

		if (studentPageTitle.equals("Students Information")) {
			System.out.println("Test Case: Passed");
		} else {
			System.out.println("Test Case: Failed");
		}

		WebElement table = driver.findElement(By.id("studentInfo"));

		int rowNum = table.findElements(By.tagName("tr")).size();

		String targetName = "Waseem";

		boolean found = false;
		for (int i = 2; i <= rowNum; i++) {
			String name = driver.findElement(By.xpath("//table//tr[" + i + "]/td[1]")).getText();
			String email = driver.findElement(By.xpath("//table//tr[" + i + "]/td[2]")).getText();
			String phone = driver.findElement(By.xpath("//table//tr[" + i + "]/td[3]")).getText();

			if (targetName.equals(name)) {
				System.out.println("Person - " + targetName + " - found:");
				System.out.println("Email: " + email);
				System.out.println("Phone Number: " + phone);
				found = true;
				break;
			}
		}

		if (!found) {
			System.out.println("Person - " + targetName + " - not found");
		}

		Keywords.endTest(driver);
	}

}
