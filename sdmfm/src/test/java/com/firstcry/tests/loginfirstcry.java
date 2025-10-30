package com.firstcry.tests;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
 
import com.firstcry.base.BaseTest;
import com.firstcry.pages.loginpage;
import com.firstcry.utilities.ExcelUtilities;
import com.firstcry.utilities.Screenshots;
import com.aventstack.extentreports.ExtentTest;
 
public class loginfirstcry extends BaseTest{
	
	static String projectpath=System.getProperty("user.dir");
	
	
	@Test(dataProvider="logindata")
	public void verifylogin(String username,String password) throws IOException
	
	{
		navigateurl("https://www.firstcry.com/");
		test=extent.createTest("login with the user:"+username);
		
		loginpage obj=new loginpage(driver);
		
		String title=driver.getTitle();
		System.out.println("title;"+title);
		if(title.equalsIgnoreCase("OrangeHRM"))
		{
			test.pass("title is matched");
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
			String screenpath=Screenshots.Capture(driver, "Title is not matched");
			
			test.fail("title is not matched:"+title).addScreenCaptureFromPath(screenpath);		
		}
		
	}
	
	
	@DataProvider
	public Object[][] logindata() throws InvalidFormatException, IOException{
		
		String excelpath=projectpath+"\\src\\test\\resources\\TestData\\data.xlsx";
		System.out.println("excelpath:"+excelpath);
		return ExcelUtilities.getdata(excelpath, "Sheet1");
		
	

}

}