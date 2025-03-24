package practise.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgTest {

	public static void main(String[] args) throws Throwable {
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
		
		
		
		FileInputStream fis1 =new FileInputStream("./testdata/Excel_DDT_Practise.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Sheet1");
		Row row =sh.getRow(1);
		String org = row.getCell(0).toString() + randomInt;
		System.out.println(org);
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
		
		//verify the url
		
		if(driver.getTitle().contains("vtiger"))
			System.out.println("vtiger home page is dispalyed");
		
		//login
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//verify the login
		
		String homelogo= driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		if(homelogo.contains("Home"))
			System.out.println("login success");
        
		//navigate to organization module
		
		driver.findElement(By.linkText("Organizations")).click();
		
		//click on create organization button
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//enter all the details and create new organization

		driver.findElement(By.name("accountname")).sendKeys(org);
		driver.findElement(By.name("button")).click();
		
		
		// verify header message expected result
		
		String orginfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orginfo.contains(org))
			System.out.println("created");
		
		//logout
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();


	

	}

}
