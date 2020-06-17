package Karros_Test;

import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

//Question: Verify sorting of First Name column

public class SortFristName {
	WebDriver driver;
	@BeforeTest
	public void Login() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://ktvn-test.s3-website.us-east-1.amazonaws.com/signin");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MICROSECONDS);
		driver.findElement(By.id("formHorizontalEmail")).sendKeys("admin@test.com");
		driver.findElement(By.id("formHorizontalPassword")).sendKeys("test123");
		driver.findElement(By.cssSelector("a.col-login__btn")).click();
	}
	
	@Test(priority = 0)
	public void sortname_descending() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> webelement_beforesort = (List<WebElement>) driver.findElements(By.xpath("//tr/td[6]"));
		List<String> list_expected = new ArrayList<String>();
		int i = 0;
		for (i = 0; i < webelement_beforesort.size(); i++) {
			list_expected.add(webelement_beforesort.get(i).getText());
		}
		Collections.sort(list_expected, new Comparator<String>() {
			//@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		driver.findElement(By.xpath("//th[@title='First Name']")).click();
		List<WebElement> webelement_name = (List<WebElement>) driver.findElements(By.xpath("//tr/td[6]"));
		List<String> list_name = new ArrayList<String>();
		for (i = 0; i < webelement_name.size(); i++) {
			list_name.add(webelement_name.get(i).getText());
		}
		for (i = 0; i < list_name.size(); i++)
			Assert.assertEquals(list_name.get(i), list_expected.get(i));
	}

	@Test(priority = 1)
	public void sortname_ascending() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> webelement_beforesort = (List<WebElement>) driver.findElements(By.xpath("//tr/td[6]"));
		List<String> list_expected = new ArrayList<String>();
		int i = 0;
		for (i = 0; i < webelement_beforesort.size(); i++) {
			list_expected.add(webelement_beforesort.get(i).getText());
		}
		Collections.sort(list_expected);

		driver.findElement(By.xpath("//th[@title='First Name']")).click();
		List<WebElement> webelement_name = (List<WebElement>) driver.findElements(By.xpath("//tr/td[6]"));
		List<String> list_name = new ArrayList<String>();
		for (i = 0; i < webelement_name.size(); i++) {
			list_name.add(webelement_name.get(i).getText());
		}
		for (i = 0; i < list_name.size(); i++)
			Assert.assertEquals(list_name.get(i), list_expected.get(i));
	}

	@AfterTest
	public void TearDown() {
		driver.quit();
	}
}
