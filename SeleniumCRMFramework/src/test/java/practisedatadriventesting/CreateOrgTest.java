package practisedatadriventesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CreateOrgTest {
	@Test
	public void CreateOrgTest() throws Throwable
	{
		String URL = System.getProperty("url");
		String BROWSER = System.getProperty("browser");
		String USERNAME = System.getProperty("username");
		String PASSWORD = System.getProperty("password");
		FileInputStream fis =new FileInputStream("C:\\Users\\ASUS\\Desktop\\Excel_DDT_Practise.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		Row row =sh.getRow(1);
		String orgName = row.getCell(2).toString();
		wb.close();
		WebDriver driver= null;
		if(BROWSER.equals("Chrome")) {
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("Firefox")) {
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver=new ChromeDriver();
		}
			else {
				driver=new ChromeDriver();
			
			}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		driver.findElement(By.id("user-name")).sendKeys(USERNAME);
		driver.findElement(By.id("password")).sendKeys(PASSWORD);
		driver.findElement(By.id("login-button")).click();
			
		}
		
		
	}


