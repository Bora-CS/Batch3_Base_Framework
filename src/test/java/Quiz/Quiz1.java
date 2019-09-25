package Quiz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class Quiz1 {
	List<Quiz1Data> data;
	//Search by name or email or phone!
	public static String name = "";
	public static String email= "";
	public static String phone= "202-202-2022";
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://bora-test.s3-website-us-east-1.amazonaws.com/");
		String homePage = driver.findElement(By.xpath("//h1")).getText();

		if (homePage.equals("Bora UI Automation Test")) {
			System.out.println("Test Case 1");
			System.out.println("Homepage Verification -- Bora UI Automation Test");
			System.out.println("Test Case 1 Status:  Passed!");
			System.out.println("============================");
		}

		driver.findElement(By.xpath("//a[@href=\"students.html\"]")).click();
		String studentPage = driver.findElement(By.xpath("//h1")).getText();
		System.out.println(studentPage);
		if (studentPage.equals("Students Information")) {
			System.out.println("Test Case 2");
			System.out.println("Homepage Verification -- Student Information");
			System.out.println("Test Case 2 Status:  Passed!");
			System.out.println("============================");
		}
		
		
		List<WebElement> studentsName = driver.findElements(By.xpath("//tr/td[1]"));
		List<WebElement> studentsEmail = driver.findElements(By.xpath("//tr/td[2]"));
		List<WebElement> studentsPhone = driver.findElements(By.xpath("//tr/td[3]"));
		
		
		System.out.println("Records found = "+studentsName.size());
		System.out.println("=========================");
		for (int i = 0; i < studentsName.size(); i++) {
			if (studentsName.get(i).getText().equals(name)) {
				System.out.println(studentsName.get(i).getText() + " -- " + studentsEmail.get(i).getText() + " -- "
						+ studentsPhone.get(i).getText());
			}
			else if (studentsEmail.get(i).getText().equals(email)) {
				System.out.println(studentsName.get(i).getText() + " -- " + studentsEmail.get(i).getText() + " -- "
						+ studentsPhone.get(i).getText());
				
			}else if (studentsPhone.get(i).getText().equals(phone)) {
				System.out.println(studentsName.get(i).getText() + " -- " + studentsEmail.get(i).getText() + " -- "
						+ studentsPhone.get(i).getText());
			}
		}
	}
}
