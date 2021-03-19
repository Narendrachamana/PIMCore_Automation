package com.Breville.Utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.apache.pdfbox.pdmodel.PDDocument;
import org.testng.Assert;

import com.Breville.Base.BaseSetup;

public class WrapperMethods extends BaseSetup {

	private static final Logger log = LogManager.getLogger(BaseSetup.class);
	
	private static final boolean DEFAULT_VERIFY_CONTAINS = true;

	public static void waitUntilElementIsVisible(WebElement element) {
		Wait<WebDriver> wait = new WebDriverWait(driver, 60).ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitUntilElementIsClickable(WebElement element) {
		Wait<WebDriver> wait = new WebDriverWait(driver, 60).ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void enterText(WebElement element, String text) {
		try {
			waitUntilElementIsVisible(element);
			element.clear();
			element.sendKeys(text);
		} catch (StaleElementReferenceException e) {
			log.warn("Caught StaleElementReferenceException in setText()." + e);
			element.clear();
			element.sendKeys(text);
		}
	}

	public static void clickElement(WebElement element) {
		try {
			waitUntilElementIsClickable(element);
			element.click();
		} catch (StaleElementReferenceException e) {
			log.warn("Caught StaleElementReferenceException in clickElement(). " + e);
			waitUntilElementIsClickable(element);
			element.click();
		}
	}
	
	public static void switchToFrame(WebElement element) {
		waitUntilElementIsClickable(element);
		driver.switchTo().frame(element);
	}
	
	public static void scrollToViewTillElement(WebElement element) {
		waitUntilElementIsClickable(element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}


	public static boolean isElementExists(By by) {
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			boolean exists = !driver.findElements(by).isEmpty();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			return exists;
		} catch (NoSuchElementException e) {
			log.warn(e);
			return false;
		}
	}

	public static void waitUntilSpinnerNotVisible() {
		if (isElementExists(By.xpath("//*[@class='spinner']"))) {
			Wait<WebDriver> wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@class='spinner']"))));
		}
	}

	public static void waitForSpecificTime(long timeToWait) {
		try {
			TimeUnit.SECONDS.sleep(timeToWait);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static boolean confirmDropDownText(WebElement element, String value) {
		try {
			return new Select(element).getFirstSelectedOption().getText().equalsIgnoreCase(value);
		} catch (StaleElementReferenceException e) {
			log.warn("Caught StaleElementReferenceException in confirmDropDownText(). " + e);
			return false;
		}
	}

	public static void selectFromDropdownByVisibleTextAndConfirm(WebElement element, String text) {
		int confirmCount = 1;
		waitUntilElementIsClickable(element);
		new Select(element).selectByVisibleText(text);
		while (!confirmDropDownText(element, text) && confirmCount < 3) {
			new Select(element).selectByVisibleText(text);
			confirmCount++;
		}
	}

	public static void selectFromDropdownByValue(WebElement element, String value) {
		try {
			waitUntilElementIsVisible(element);
			selectFromDropdownByValueAndConfirm(element, value);
		} catch (StaleElementReferenceException e) {
			log.warn("Caught StaleElementReferenceException in selectFromDropdownByValue()" + e);
			selectFromDropdownByValueAndConfirm(element, value);
		}
	}

	private static void selectFromDropdownByValueAndConfirm(WebElement element, String value) {
		int confirmCount = 1;
		waitUntilElementIsClickable(element);
		new Select(element).selectByValue(value);
		while (!confirmDropDownValue(element, value) && confirmCount < 3) {
			new Select(element).selectByValue(value);
			confirmCount++;
		}
	}

	private static boolean confirmDropDownValue(WebElement element, String value) {
		try {
			return new Select(element).getFirstSelectedOption().getAttribute("value").equalsIgnoreCase(value);
		} catch (StaleElementReferenceException e) {
			log.warn("Caught StaleElementReferenceException in confirmDropDownValue(). " + e);
			return false;
		}

	}

	public  static void selectFromDropdownByIndex(WebElement element, int index) {
		waitUntilElementIsClickable(element);
		new Select(element).selectByIndex(index);
	}

	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static boolean waitForAjax() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		ExpectedCondition<Boolean> jQueryLoad;
		if ((Boolean) ((JavascriptExecutor) driver).executeScript("return window.jQuery != undefined")) {
			jQueryLoad = (dvr -> ((boolean) ((JavascriptExecutor) dvr).executeScript("return jQuery.active == 0")));
		} else {
			jQueryLoad = (dvr -> true);
		}
		ExpectedCondition<Boolean> jsLoad = (dvr -> "complete"
				.equals(((JavascriptExecutor) dvr).executeScript("return document.readyState").toString()));
		ExpectedCondition<Boolean> spinnerNotActive = (dvr -> (!(boolean) ((JavascriptExecutor) dvr)
				.executeScript("return document.activeElement == document.getElementsByClassName('spinner')")));
		return wait.until(jQueryLoad) && wait.until(jsLoad) && wait.until(spinnerNotActive);
	}

	public  static String getText(WebElement element) {
		try {
			waitUntilElementIsVisible(element);
			return element.getText();
		} catch (StaleElementReferenceException e) {
			log.warn("Caught StaleElementReferenceException in getText(). " + e);
			return element.getText();
		}
	}

	public void alertHandle(boolean accept, String answer) {
		Alert alert = null;

		switch (browser) {

		case "chrome":
			if (answer != null) {
				alert.sendKeys(answer);
			}
			if (accept) {
				driver.switchTo().alert().accept();
			} else {
				driver.switchTo().alert().dismiss();
			}

			break;
		case "edge":
			if (answer != null) {
				alert.sendKeys(answer);
			}
			if (accept) {
				driver.switchTo().alert().accept();
			} else {
				driver.switchTo().alert().dismiss();
			}
			break;
		case "firefox":
			break;

		case "ie":
			try {
				Thread.sleep(4000);
				pressEnter();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		break;

		}
	}

	public static String getScreenShot() {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator
				+ System.currentTimeMillis() + ".png";
		System.out.println(path);
		File des = new File(path);
		try {
			FileUtils.copyFile(src, des);
		} catch (IOException e) {
			log.info("Exception Wait for Element>>" + e.getMessage());
		}
		return path;

	}
	
	
	public static String verifyDownloadedFile(String filePath,String fileName) {	  

		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();

		boolean found = false;
		
		String fileName1 = "";

		File f = null;
		
		for (File listOfFile : listOfFiles) {

			if (listOfFile.isFile()) {
				fileName1 = listOfFile.getName();
				if (fileName1.contains(fileName)) {
					f = new File(fileName1);
					found = true;
					break;
				}
			}
		}
		
		Assert.assertTrue(found ,"Downloaded document is not found");
		log.info("Downloaded file is found");
	
		f.deleteOnExit();
		
		return fileName1;
	}
	
	public static boolean deleteDownloadedFile(String path) {
		boolean status = false ;
	try 
	{
	if ((new File(path)).delete()) {
	               status= true;
	            } else {
	            	status= false;
	            }

	          } catch (Exception ex) {
	            ex.printStackTrace();
	          }
	return status;
	}
	
	public static String convertStringArrayToString(Object[][] data1, String delimiter) {
		StringBuilder sb = new StringBuilder();
		for (Object[] str : data1)
			sb.append(str).append(delimiter);
		return sb.substring(0, sb.length() - 1);
	}

	public static String getProductData(String path, String sheetName, int colNumber ,boolean flag) throws Exception {
		Object[][] excelData = ExcelReader.getDataFromSpreadSheet(path, sheetName, colNumber);
		int length = excelData.length;
		StringBuilder sb = new StringBuilder();
		String data = "";

		for (int i = 0; i < length; i++) {
			for (int j = 0; j <= 0; j++) {
				data = (String) excelData[i][j];
				if(data != null && data != " " && data !="") {
				sb.append(data);
				}
				if(flag)
					{
					sb.append(",");
					}
				else {
					sb.append(" ");
				}

			}
		}

		data = sb.toString().substring(0, sb.toString().length()-1);

		return data;
	}
	
	
	private static void ctrlV(String stringToPaste) throws AWTException{
	    Robot robot = new Robot();
	    StringSelection strToPaste = new StringSelection(stringToPaste);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strToPaste, null);            
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	private static void pressTab() throws AWTException{
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_TAB);
	    robot.keyRelease(KeyEvent.VK_TAB);
	}

	private static void pressEnter() throws AWTException{
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	}

}
