package demotest.day03;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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

	public static void login(String username, String password) throws InterruptedException {
		WebDriver driver = initDriver();

		// launch Chrome Browser and open the Base URL
		driver.get(Util.BASEURL);

		// Enter username
		driver.findElement(By.name("uid")).sendKeys(username);

		// Enter Password
		driver.findElement(By.name("password")).sendKeys(password);

		// Click Login
		driver.findElement(By.name("btnLogin")).click();

		// Find welcome page locator
		//driver.findElement(By.tagName("marquee"));

		// Close browser and driver
		Thread.sleep(5000);
		driver.quit();

	}

	@SuppressWarnings("deprecation")
	public static Map<Integer, List<String>> getDataFromExcel() throws IOException {

		FileInputStream file = new FileInputStream(new File("./testData.xls"));
		Workbook workbook = new HSSFWorkbook(file);

		Sheet sheet = workbook.getSheetAt(0);

		Map<Integer, List<String>> data = new HashMap<Integer, List<String>>();
		int i = 0;
		for (Row row : sheet) {
			data.put(i, new ArrayList<String>());
			for (Cell cell : row) {
				switch (cell.getCellTypeEnum()) {
				case STRING:
					data.get(new Integer(i)).add(cell.getRichStringCellValue().getString());
					break;
				case NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						data.get(i).add(cell.getDateCellValue() + "");
					} else {
						data.get(i).add(cell.getNumericCellValue() + "");
					}
					break;
				case BOOLEAN:
					data.get(i).add(cell.getBooleanCellValue() + "");
					break;
				case FORMULA:
					data.get(i).add(cell.getCellFormula() + "");
					break;
				default:
					data.get(new Integer(i)).add(" ");
				}
			}
			i++;
		}
		if (workbook != null) {
			workbook.close();
		}

		return data;
	}

	public static Map<String, String> parseDataToMap(Map<Integer, List<String>> data) {
		Map<String, String> testData = new HashMap<String, String>();
		for (Integer key : data.keySet()) {
			if (!(key == 0 || key == data.keySet().size() - 1)) {
				// System.out.println(key);
				testData.put(data.get(key).get(0), data.get(key).get(1));
			}
		}
		return testData;
	}

	public static void main(String[] args) throws InterruptedException {
		Map<String, String> testData = new HashMap<String, String>();
		/*
		 * login(Util.VALID_USERNAME, Util.VALID_PASSWORD); login(Util.INVALID_USERNAME,
		 * Util.VALID_PASSWORD); login(Util.VALID_USERNAME, Util.INVALID_PASSWORD);
		 * login(Util.INVALID_USERNAME, Util.INVALID_PASSWORD);
		 */

		try {
			testData = parseDataToMap(getDataFromExcel());
			//System.out.println(testData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Map.Entry<String, String> entry : testData.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			login(key, value);
		}
	}

}
