package practice.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactWithOrg {

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
		String org = row.getCell(3).toString() + randomInt;
		//System.out.println(org);
		String contactlastname = row.getCell(0).toString();
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
		//save
		
		driver.findElement(By.name("button")).click();
	
		
		
		// verify header message expected result
		
		String orginfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orginfo.contains(org))
			System.out.println("created");
		
		//logout
		//Actions act = new Actions(driver);
		//act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click().perform();
		//driver.findElement(By.linkText("Sign Out")).click();
		//driver.quit();
		
		
		//navigate to contact module

				driver.findElement(By.linkText("Contacts")).click();
				
				//click on create contacts button
				
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

				driver.findElement(By.name("lastname")).sendKeys(contactlastname);
				
				// click on org(+)
				
				driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
				
				//switch to child window
			 Set<String> set =driver.getWindowHandles();
			 for(String windowid: set)
			 { 
				 driver.switchTo().window(windowid);
				 String acturl=driver.getCurrentUrl();
				 if(acturl.contains("module=Accounts"))
				 {
					 break;
			 
			 }
			 }
				
				
				driver.findElement(By.name("search_text")).sendKeys(org);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[text()='"+org+"']")).click();
				
				//switch to parent window
				Set<String> set1 =driver.getWindowHandles();
				 for(String windowid: set1)
				 { 
					 driver.switchTo().window(windowid);
					 String acturl=driver.getCurrentUrl();
					 if(acturl.contains("Contacts&action"))
					 {
						 break;
				 
				 }
				 }
				 
				 //save the contact page
				 
				 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				 
				 //verify header orgname info expected result
				 String orginfo1=driver.findElement(By.id("mouseArea_Organization Name")).getText();
				 System.out.println(orginfo1);
					if(orginfo1.trim().equals(org))  //trim()..used for ignore the space b/w them.
						System.out.println("created");
					else
						System.out.println("fail");
					driver.quit();


	}

}
