package practise.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
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

public class CreateOrgWithPhone {

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
		
		
		FileInputStream fis1 =new FileInputStream("C:\\Users\\ASUS\\eclipse-workspace\\SeleniumCRMFramework\\teatdataphone\\IndustryExcel_DDT.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Sheet1");
		Row row =sh.getRow(7);
		String org = row.getCell(2).toString() + randomInt;
		String phonenumber = row.getCell(3).toString();
		System.out.println(org);
		
		wb.close();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
		//login 
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//navigate to org module

		driver.findElement(By.linkText("Organizations")).click();
		
		// enter all details and create new org
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(org);
		
		//enter phone number
	
		driver.findElement(By.id("phone")).sendKeys(phonenumber);
		driver.findElement(By.name("button")).click();
		
		// verify header phone number
		String actphonenumber= driver.findElement(By.id("dtlview_Phone")).getText();
		if(actphonenumber.equals(phonenumber)) {
			System.out.println("pass");
		}
		
		

	


	}

}
