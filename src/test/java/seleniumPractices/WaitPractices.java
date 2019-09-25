package seleniumPractices;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import Utilities.Keywords;
public class WaitPractices {
public static void main(String[] args) {
	WebDriver driver = Keywords.startTestOnChrome("https://tympanus.net/Development/PageLoadingEffects/index.html");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	
	
	
	
	driver.close();
	driver.quit();
}
}
