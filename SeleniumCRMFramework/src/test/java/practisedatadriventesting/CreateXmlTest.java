package practisedatadriventesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class CreateXmlTest {
	@Test
	
	public void createxmltest(XmlTest test) throws Throwable {
     JSONParser parser = new JSONParser();
		
        
		Object obj= parser.parse(new FileReader("C:\\Users\\ASUS\\Desktop\\AppData.json"));
		JSONObject map=(JSONObject)obj;
	
	

    String BROWSER =  test.getParameter("browser");
	String URL = test.getParameter("url");
	String USERNAME = test.getParameter ("username");
	String PASSWORD = test.getParameter ("password");
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





