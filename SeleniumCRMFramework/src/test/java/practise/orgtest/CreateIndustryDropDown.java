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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateIndustryDropDown {

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
		
		
		FileInputStream fis1 =new FileInputStream("C:\\Users\\ASUS\\eclipse-workspace\\ComcastCRMFramework\\testdata1\\IndustryExcel_DDT.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Sheet1");
		Row row =sh.getRow(4);
		String org =row.getCell(2).toString() + randomInt;
		String industry = row.getCell(3).toString();
		String type = row.getCell(4).toString();
		System.out.println(org);
		wb.close();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//login
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//navigate to organization module
		
		driver.findElement(By.linkText("Organizations")).click();
		
		//click on create organization button

		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//enter all the details and create new organization
		
		
		driver.findElement(By.name("accountname")).sendKeys(org);

	// industry dropdown
		
		WebElement ind= driver.findElement(By.name("industry"));
		Select obj =new Select(ind);
		obj.selectByVisibleText(industry);
	 
		//type dropdown
		
		WebElement type1= driver.findElement(By.name("accounttype"));
		Select obj1= new Select(type1);
		obj1.selectByVisibleText(type);

        //save 
		driver.findElement(By.name("button")).click();
		
		//verify the industry and type info
		String actIndustry= driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustry.equals(industry)) {
			
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
		
		String acttype= driver.findElement(By.id("mouseArea_Type")).getText();
		if(acttype.equals(type)) {
			
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
	


	



	}

}
