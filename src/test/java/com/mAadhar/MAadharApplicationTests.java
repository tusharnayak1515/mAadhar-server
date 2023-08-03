package com.mAadhar;

import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest
class MAadharApplicationTests {

	private WebDriver driver;
	private static String url = "http://localhost:4200";
	public SoftAssert soft = new SoftAssert();
	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	public static WebDriverWait wait;

	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@BeforeTest(groups = "chrome")
	public void Setup(ITestContext context) {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));

		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();

		String device = capabilities.getBrowserName() + "<===>" + capabilities.getBrowserVersion();
		String author = "Tushar Ranjan Nayak";

		extentTest = extentReport.createTest(context.getName());
		extentTest.assignAuthor(author);
		extentTest.assignDevice(device);
	}

	@Test(groups = "chrome")
	public void OpenClientTest() throws Exception {
		try {
			driver.get(url);
			Thread.sleep(2000);
			soft.assertTrue(driver.getTitle().equalsIgnoreCase("mAadhar"));
			soft.assertAll();
			extentTest.pass("Assertion is passed for Opening mAadhar Angular app");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(groups = "chrome", dependsOnMethods = "OpenClientTest")
	public void LoginUserTest() throws Exception {
		try {
			wait.until(ExpectedConditions.urlContains("login"));
			Thread.sleep(2000);

			WebElement mobile = driver.findElement(By.id("mobile"));
			mobile.sendKeys("8658982115");
			Thread.sleep(2000);
			WebElement password = driver.findElement(By.id("password"));
			password.sendKeys("123456");
			Thread.sleep(2000);
			WebElement loginForm = driver.findElement(By.id("loginForm"));
			loginForm.submit();

			wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.urlContains("dashboard"));

			soft.assertTrue(driver.getCurrentUrl().contains("dashboard"));

			Thread.sleep(2000);

			soft.assertAll();
			extentTest.pass("Assertion is passed for Log in mAadhar Angular app");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(groups = "chrome", dependsOnMethods = "LoginUserTest")
	public void LogoutUserTest() throws Exception {
		try {
			Thread.sleep(2000);
			WebElement logoutBtn = driver.findElement(By.id("logout"));
			logoutBtn.click();
			Thread.sleep(2000);

			wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.urlContains("login"));

			soft.assertTrue(driver.getCurrentUrl().contains("login"));

			soft.assertAll();
			extentTest.pass("Assertion is passed for Logout mAadhar Angular app");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterTest(groups = "chrome")
	public void CloseProcess()
	{
		driver.quit();
	}

	@BeforeSuite(groups = "chrome")
	public void InitializeReport()
	{
		ExtentSparkReporter  sparkreport = new ExtentSparkReporter("AllTests.html");
		sparkreport.config().setReportName("All Test Report");
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkreport);
		
		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));	
	}

	@AfterSuite(groups = "chrome")
	public void generateReport()
	{
		extentReport.flush();
	}

	@AfterMethod(groups = "chrome")
	public void checkStatus(Method m, ITestResult result)
	{
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			extentTest.fail(result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			extentTest.pass(m.getName() + " is Passed");
		}
		
		extentTest.assignCategory(m.getAnnotation(Test.class).groups());
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
