package assignmentTask;

import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;

public class BrowserSetup 
{
	WebDriver driver = null;
	public static String status = "passed";
	String username = "shreyasdevekar";
	String access_key = "Jmon4S4FmjzGgcY8IgAWPFZCtv4lmgGvWVLH8cHEj3gIkTBDQv";
	String url = "https://www.lambdatest.com/selenium-playground";

	@BeforeMethod
	@Parameters(value={"browser","version","platform", "resolution"})
	public void Setup(String browser, String version, String platform, String resolution) throws Exception 
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("build", "LambdaTest Selenium 101 certification assignment");
		capabilities.setCapability("name", "LambdaTest Selenium 101 certification assignment");
		capabilities.setCapability("platform", platform);
		capabilities.setCapability("browserName", browser);
		capabilities.setCapability("version",version);
		capabilities.setCapability("resolution",resolution);
		capabilities.setCapability("tunnel",false);
		capabilities.setCapability("network",true);
		capabilities.setCapability("console",true);
		capabilities.setCapability("visual",true);
		capabilities.setCapability("video", true);

		try
		{
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + access_key + "@hub.lambdatest.com/wd/hub"), capabilities);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);
			driver.get(url);
			try 
			{
				WebElement accept = driver.findElement(By.xpath("//div[3]/div/span[1]"));
				Boolean acceptPopUp = accept.isDisplayed();
				if(acceptPopUp)
				{
					accept.click();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}			
		}
		catch (MalformedURLException e)
		{
			System.out.println("Invalid grid URL");
		}
		System.out.println("Started session");
	}


	@AfterMethod
	public void afterMethod()
	{
		if (driver != null)
	       {
	           ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
	           driver.quit();
	       }
	}

}
