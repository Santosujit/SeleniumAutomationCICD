package RahulShettyAcademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RahulShettyAcademy.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;// Now the driver is NULL, No Life, driver is accessible inside this class
	public LandingPage landingPage;// so that SubmitOrder class can access this landingPage object

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();// Create an Object of Property file
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\test\\java\\RahulShettyAcademy\\Resources\\GlobalData.properties");

		// The above line makes the connection between Properties file and Java
		// System.getProperty("user.dir") -> We use this not to hard code the local
		// project path. As project "ll be given to somebody else.
//		C:\Users\santosujit.mohanty\eclipse-workspace\SeleniumFrameworkDesign - This Path is local, after that inside framework

		prop.load(fis);// Load the Property File here, Fis is the FileInputStream type of object

		String browserName = prop.getProperty("browser");// Read from the Property File
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--ignore-certificate-errors");
//		options.setAcceptInsecureCerts(true);
//		options.addArguments("--ignore-ssl-errors=yes");

		browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
//		The above line is using ternary operator if condition is true, executes, immediate statement or else the execute the next one
//		System.getProperty() always gives the System variable

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			
//			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);// Here the driver got Life, send this driver to all actions methods

			// from this Test method

		} else if (browserName.equalsIgnoreCase("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
//			WebDriverManager.firefoxdriver().setup();
			driver = new EdgeDriver();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String jsonFilePath) throws IOException {
		// Read Jason to String below

		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

//		Now convert to HashMap - use jackson databind API ---> Add the dependency in pom.xml
//		As shown below ObjectMapper is class, and inside the class, readValue method reads jason and converts to string
//		As shown below TypeReference is asking which type of HashMaps you want
//		List<HashMap<String, String> stores two hashmaps, In 0th location of the List map, on 1st location of the List map1

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	@BeforeMethod(alwaysRun = true) // It will run always irrespective put in Groups or not
	// child as well as parent calss, it scans and It finds out that BeforeMethod so
	// before every test method this will run
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;// to catch this object we create a LandingPage object in submitOrder class
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

//	We want to take screen shot when the test case fails, so Let's create a utility to take screen shot as shown below
//	driver interacts with the browser, typecast it with TakesScreenshot, so that driver 'll know that It has to take screen shot or photo

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File filePath = new File(System.getProperty("user.dir") + "\\Reports" + ".png");
		FileUtils.copyFile(source, filePath);
		return System.getProperty("user.dir") + "\\Reports\\" + testCaseName + ".png";// this method returning where in
																						// local filepath screen shot
																						// will be stored
	}
}
