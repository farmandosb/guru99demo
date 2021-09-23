package demotest.day01;

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
	public static void main(String[] args) {

		// Setup Driver
		System.setProperty("webdriver.chrome.driver", Util.DRIVERLOCATION);
		WebDriver driver = new ChromeDriver();

		// launch Chrome Browser and open the Base URL
		driver.get(Util.BASEURL);

		// Enter username
		driver.findElement(By.name("uid")).sendKeys("mngr351354");

		// Enter Password
		driver.findElement(By.name("password")).sendKeys("gEzYpAb");

		// Click Login
		driver.findElement(By.name("btnLogin")).click();

		// Verify manager page is shown
		driver.findElement(By.tagName("marquee"));

		// Close browser and driver
		driver.quit();

	}

}
