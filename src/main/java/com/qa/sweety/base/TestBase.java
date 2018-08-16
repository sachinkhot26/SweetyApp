package com.qa.sweety.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class TestBase {
	
	
	public static WebDriver driver;
	public static Properties prop;
	public static FileInputStream ExcelFile;
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	public static String data[][]=null;
	
	
	public TestBase(){
		
		try{
			prop=new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/sweety/config/config.properties");
			prop.load(fis);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public static void initialization(){
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver","/Users/sachin/Desktop/selenium/chromedriver");
			driver=new ChromeDriver();
			System.out.println("Khalid Imam");
			
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			
			System.setProperty("webdriver.gecko.driver","/Users/sachin/Desktop/selenium/geckodriver");
			driver=new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
		
	}
	@Test
	public static String[][] excelReader() throws IOException {
		
			ExcelFile = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/sweety/data/sweetyapp.xlsx");
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(prop.getProperty("sheetName"));
			int totalRows=ExcelWSheet.getLastRowNum();
			int totalCols=ExcelWSheet.getRow(0).getLastCellNum();
//			System.out.println("rows=="+totalRows);
//			System.out.println("colss=="+totalCols);
			data=new String[totalRows][totalCols];
			
			
			for(int i=0;i<totalRows;i++) {
				for(int j=0;j<totalCols;j++) {
					 data[i][j]=ExcelWSheet.getRow(i+1).getCell(j).getStringCellValue();
					
				}
			}
			
			return data;
		
	}
	
	public static void screenShot() throws Exception {
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		System.out.println(currentDir);
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	
}
