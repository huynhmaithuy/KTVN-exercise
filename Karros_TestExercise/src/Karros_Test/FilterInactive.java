package Karros_Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

//Question: Verify filter Student Access Request with INACTIVE

public class FilterInactive {
	WebDriver driver;

	@BeforeTest
	public void InvokeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://ktvn-test.s3-website.us-east-1.amazonaws.com/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void login() {
		driver.findElement(By.cssSelector("input#formHorizontalEmail")).sendKeys("admin@test.com");
		driver.findElement(By.cssSelector("input#formHorizontalPassword")).sendKeys("test123");
		driver.findElement(By.cssSelector("a.col-login__btn")).click();
	}

	@Test(dependsOnMethods = "login")
	public void fillter_Inactive() {
		driver.findElement(By.cssSelector("button.module_grid__btn_filter")).click();
		Select request_status = new Select(driver.findElement(By.cssSelector("select#formControlsSelect")));
		request_status.selectByValue("inactive");
		driver.findElement(By.xpath("//*[contains(text(), 'Apply')]")).click();
		List<WebElement> list_status = driver.findElements(By.xpath("//tr/td[2]"));
		//check to see all filtered records having status of Inactive
		for (int i = 0; i < list_status.size(); i++) {
			Assert.assertEquals(list_status.get(i).getText(), "Inactive");
		}
	}

	@AfterTest
	public void TearDown() {
		driver.quit();
	}
}
