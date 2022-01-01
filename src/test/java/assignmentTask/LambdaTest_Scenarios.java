package assignmentTask;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LambdaTest_Scenarios extends BrowserSetup
{
	
	@Test(description = "Verify Entered Message")
	public void TestScenario_1() 
	{
		driver.findElement(By.linkText("Simple Form Demo")).click();
		String currentUrl = driver.getCurrentUrl();
		boolean containsFlag = currentUrl.contains("simple-form-demo");
		Assert.assertTrue(containsFlag);
		String Message = "Welcome to LambdaTest";
		driver.findElement(By.id("user-message")).click();
		driver.findElement(By.xpath("//*[@id=\"user-message\"]")).sendKeys(Message);
		driver.findElement(By.id("showInput")).click();
		String displayedMsg = driver.findElement(By.xpath("//*[@id=\"message\"]")).getText();
		Assert.assertEquals(Message,displayedMsg);
	}

	@Test(description = "Verify slider value")
	public void TestScenario_2()
	{
		driver.findElement(By.linkText("Drag & Drop Sliders")).click();
		WebElement slider = driver.findElement(By.xpath("//*[@id=\"slider3\"]/div/input"));
		WebElement sliderOutput = driver.findElement(By.id("rangeSuccess"));
		Actions move = new Actions(driver);
		move.click(slider).build().perform();
		for (int i = 0; i <= 80; i++) {
		    move.sendKeys(Keys.ARROW_RIGHT).build().perform();
		    if(sliderOutput.getText().equals("95"))
		    {
		    	break;
		    }
		}
		Assert.assertEquals(sliderOutput.getText(), "95");
	}
	
	@Test(description = "Verify input form fields")
	public void TestScenario_3()
	{
		driver.findElement(By.linkText("Input Form Submit")).click();
		driver.findElement(By.xpath("//*[@id=\"seleniumform\"]/div[6]/button")).click();
		WebElement name = driver.findElement(By.id("name"));
		String mandatoryPopUp = name.getAttribute("validationMessage");
		Assert.assertEquals(mandatoryPopUp, "Please fill in the fields");
		driver.findElement(By.id("name")).sendKeys("Shreyas");
		driver.findElement(By.id("inputEmail4")).sendKeys("shreyasdevekar@gmail.com");
		driver.findElement(By.id("inputPassword4")).sendKeys("Shreyas@123");
		driver.findElement(By.id("company")).sendKeys("ABC Ltd");
		driver.findElement(By.id("websitename")).sendKeys("ABCLimited.com");
		WebElement countryDropDown = driver.findElement(By.xpath("//select[@name=\"country\"]"));
		Select country = new Select(countryDropDown);
		country.selectByVisibleText("United States");
		driver.findElement(By.id("inputCity")).sendKeys("New York");
		driver.findElement(By.id("inputAddress1")).sendKeys("XYZ Villa");
		driver.findElement(By.id("inputAddress2")).sendKeys("Near LMN");
		driver.findElement(By.id("inputState")).sendKeys("Washington D.C.");
		driver.findElement(By.id("inputZip")).sendKeys("99301");
		driver.findElement(By.xpath("//*[@id=\"seleniumform\"]/div[6]/button")).click();
		String successMsg = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[3]/div/div/div[2]/div/p")).getText();
		String expectedMsg = "Thanks for contacting us, we will get back to you shortly.";
		Assert.assertEquals(successMsg, expectedMsg);
		
	}

}
