package SeleniumPractices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import DataObjects.AmazonSearchResult;
import Utilities.Keywords;

/*
1, Search for an item on amazon
2, Use a data structure to store below information for each result, for a total of 100 results.
	==> title
	==> price
3, Print the result in the:
	No. - Title - Price
	Item No.1 - Apple iPhone7Plus - $750
4, Calculate the average price based on the result
*/


/*
 * ArrayList<SomeType> has index
 * HashMap<SomeType, AnotherType> 
 * 
 * ItemID, Title, Price
 * 
 * Options:
 * ArrayList<HashMap> 
 * ArrayList<String[]>
 * HashMap<int, HashMap>
 * HashMap<int, String[]> *** recommended
 * 
 */

public class AmazonTest {

	public static String searchItem = "iPhone Case";
	public static WebDriver driver;
	public static int numberOfResults = 0;
	public static double sum = 0.0;
	public static String parentXpath = "//span[@data-component-type='s-search-results']/div[@class='s-result-list s-search-results sg-row']/div";
	public static String titleXpath = "//span[@class='a-size-base-plus a-color-base a-text-normal']";
	public static String priceXpath = "//span[@class='a-price']";
	public static List<AmazonSearchResult> data;

	public static void main(String[] args) {
		try {
			driver = Keywords.startTestOnChrome("https://www.amazon.com/");
			data = new ArrayList<AmazonSearchResult> ();
			action();
			
			System.out.println("Retrieving data no.33");
			System.out.println("Title:" + data.get(33).ItemName);
			System.out.println("Price:" + data.get(33).ItemPrice);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Keywords.endTest(driver);
		}
	}

	private static void action() {
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys(searchItem);
		searchBox.submit();
		
		while (numberOfResults <= 100) {
			getResults();
			if (numberOfResults == 100) {
				break;
			} 
			driver.findElement(By.xpath("//*[@class=\"a-last\"]/a")).click();
		}
		
		System.out.println();
		double average = sum/100;
		String averageS = average + "";
		averageS = averageS.substring(0, averageS.indexOf(".")+3);
		System.out.println("Average price for "+searchItem+" on amazon is $" + averageS);
	}

	public static void getResults() {
		List<WebElement> results = driver.findElements(By.xpath(parentXpath));
		for (int i = 1; i <= results.size(); i++) {
			if (numberOfResults == 100) {
				break;
			} 
		
			String title;
			String price;
			try {
				title = driver.findElement(By.xpath(parentXpath + "[" + i + "]" + titleXpath)).getText();
			} catch (Exception e) {
				continue;
			}
			try {
				price = driver.findElement(By.xpath(parentXpath + "[" + i + "]" + priceXpath)).getText();
				price = price.replace("\n", ".");
			} catch (Exception e) {
				continue;
			}
	
			numberOfResults++;
			double priceD = Double.parseDouble(price.replace("$", ""));
			sum+=priceD;

			data.add(new AmazonSearchResult(numberOfResults, title, priceD));
			
		}
	}

}
