package practice.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateContactWithSupportDate {

	public static void main(String[] args) throws Throwable, IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("C:\\Users\\ASUS\\Desktop\\vtigerdemo.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER= pObj.getProperty("browser");
		String URL= pObj.getProperty("url");
		String USERNAME= pObj.getProperty("username");
		String PASSWORD= pObj.getProperty("password");
		System.out.println(URL);
		Random random = new Random();
		int randomInt=random.nextInt(500);
		
		
		FileInputStream fis1 =new FileInputStream("C:\\Users\\ASUS\\eclipse-workspace\\SeleniumCRMFramework\\exceldata\\lastsheetExcel.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Sheet1");
		Row row =sh.getRow(1);
		String lastname= row.getCell(2).toString() + randomInt;
		System.out.println(lastname);
		
		wb.close();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
		//login
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//navigate to contact module

		driver.findElement(By.linkText("Contacts")).click();
		
		//click on create contacts button
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		Date dateobj = new Date();
		SimpleDateFormat sim = new 	SimpleDateFormat("yyyy-mm-dd");
		
		String startDate=sim.format(dateobj);
		
		 Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String endDate = sim.format(cal.getTime());
		
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		//clear the current date
		driver.findElement(By.name("support_start_date")).clear();
		//write the current date
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		//clear the end date
		driver.findElement(By.name("support_end_date")).clear();
		
		//write the end date
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		
		
		// save
		
		driver.findElement(By.name("button")).click();
		
		//verify the contact header
		
		String actlastname= driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actlastname.equals(lastname)) {
			
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
		
		
		
		

		

	}

}
