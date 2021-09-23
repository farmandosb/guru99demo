package demotest.day02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import demotest.Util;

/*
 *@author Freddy Suarez
 *		Test01_Login
 *		************
 *		Test Steps
 *		1) Go to http://www.demo.guru99.com/V4/
 *		2) Enter valid User id
 *		3) Enter valid Password
 *		4) Click Login
 *		5) Verify the output
 */

public class Test01_Login {

	public static WebDriver initDriver() {
		System.setProperty("webdriver.chrome.driver", Util.DRIVERLOCATION);
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	public static void login(String username, String password) {
		WebDriver driver = initDriver();

		// launch Chrome Browser and open the Base URL
		driver.get(Util.BASEURL);

		// Enter username
		driver.findElement(By.name("uid")).sendKeys(Util.VALID_USERNAME);

		// Enter Password
		driver.findElement(By.name("password")).sendKeys(Util.VALID_PASSWORD);

		// Click Login
		driver.findElement(By.name("btnLogin")).click();

		// Find welcome page locator
		driver.findElement(By.tagName("marquee"));

		// Close browser and driver
		driver.quit();

	}

	public static void main(String[] args) {

		login("mngr351354", "gEzYpAb");
		login("mngr333333", "gEzYpAb");
		login("mngr351354", "gEzYYYY");
		login("mngr333333", "gEzYYYY");

	}

}
