package DemoRestAssuredProject.DemoRestAssuredProject;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

public class AppTest {

	@Test
	public void loginTest() {
	
		/*
		 *  downloads the right driver binary, and configures it.
		 *  WebDriverManager.chromedriver().setup(); 
		 *  WebDriverManager.chromedriver().driverVersion("127.0.6533.72").setup();
		 */
		

		/*
		 * ChromeOptions options = new ChromeOptions();
		 * options.addArguments("--start-maximized");
		 * options.addArguments("--disable-notifications");
		 */

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.close();
	}

}
