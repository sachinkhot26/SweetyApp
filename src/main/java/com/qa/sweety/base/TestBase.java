package com.qa.sweety.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
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
	public static HashMap<Object, Object>hmap;
	
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
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/drivers/chromedriver");
			driver=new ChromeDriver();
			
			
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/drivers/geckodriver");
			driver=new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
		
	}
	//@Test
	public static void excelReader(int rownum) throws IOException {
		
			ExcelFile = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/sweety/data/sweetyapp.xlsx");
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(prop.getProperty("sheetName"));
			int totalRows=ExcelWSheet.getLastRowNum()-ExcelWSheet.getFirstRowNum();
			int totalCols=ExcelWSheet.getRow(0).getLastCellNum();
//			System.out.println("rows=="+totalRows);
//			System.out.println("colss=="+totalCols);
			data=new String[rownum][totalCols];
			
			
			for(int i=0;i<1;i++) {
				for(int j=0;j<totalCols;j++) {
					 data[i][j]=ExcelWSheet.getRow(rownum).getCell(j).getStringCellValue();
					
				}
			}
			
		
		
	}
	@Test
	
	public static HashMap<Object, Object> excelReaderNew(String testcaseName) throws Exception
	{
		
		ExcelFile = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/sweety/data/sweetyappp.xlsx");
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(prop.getProperty("sheetName"));
		int totalRows=ExcelWSheet.getLastRowNum()-ExcelWSheet.getFirstRowNum();
		int totalCols=ExcelWSheet.getRow(0).getLastCellNum();
		
		
		for(int i=1; i<totalRows+1;i++) {
			
			String testcase= ExcelWSheet.getRow(i).getCell(0).getStringCellValue();
			
			
			if(testcase.equals(testcaseName)) {
				
			    	hmap=new HashMap<Object, Object>();
					hmap.put("email",ExcelWSheet.getRow(i).getCell(1).getStringCellValue());
					hmap.put("password",ExcelWSheet.getRow(i).getCell(2).getStringCellValue() );
					
			
			}
			else {
				continue;
			}
			
			
		}
		return hmap;
	}
	
	public static void screenShot() throws Exception {
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	
}
