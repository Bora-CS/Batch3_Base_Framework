package com.boratech.seleniumtest;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.boratech.util.Keywords;

public class CostcoWaitHW {
public static void main(String[] args) {
	WebDriver driver = Keywords.startTestOnchrome("https://www.costco.com/");

    try {

    WebDriverWait wait = new WebDriverWait(driver, 45);
    wait.until(ExpectedConditions.presenceOfElementLocated
            (By.xpath("//a[@href='/CatalogSearch?dept=All&keyword=appleseptmvm19'][@role='tabpanel'][@aria-hidden='false']")));
    driver.findElement(By.id("hero-carouse")).click();
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
    
    } catch (Exception e) {
        System.out.println(e);
        Keywords.endTest(driver);

    }
    
}

}