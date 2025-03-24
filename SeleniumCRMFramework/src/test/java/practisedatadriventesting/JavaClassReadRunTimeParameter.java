package practisedatadriventesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class JavaClassReadRunTimeParameter {

	@Test
	public void RunTimeParameterTest() throws Throwable
	{
		String URL =System.getProperty("url");
		String BROWSER =System.getProperty("browser");
		String USERNAME =System.getProperty("username");
		String PASSWORD =System.getProperty("password");
		System.out.println("Env Data==>URL====>"+URL);
		System.out.println("Env Data==>BROWSER====>"+BROWSER);
		System.out.println("Env Data==>USERNAME====>"+USERNAME);
		System.out.println("Env Data==>PASSWORD====>"+PASSWORD);
		
		
		
		
	
		

	}

}
